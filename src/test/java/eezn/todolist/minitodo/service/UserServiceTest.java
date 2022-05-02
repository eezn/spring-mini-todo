package eezn.todolist.minitodo.service;

import eezn.todolist.minitodo.AppConfig;
import eezn.todolist.minitodo.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    static UserService userService;

    static User user1, user2, user3;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/scheme.sql")
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AppConfig appConfig = new AppConfig(jdbcTemplate);

        userService = appConfig.userService();

        user1 = new User();
        user2 = new User();
        user3 = new User();

        user1.setUsername("TEST_USER1");
        user1.setPassword("TEST_USER1_PASSWORD");
        user1.setEmail("TEST_USER1_EMAIL");

        user2.setUsername("TEST_USER2");
        user2.setPassword("TEST_USER2_PASSWORD");
        user2.setEmail("TEST_USER2_EMAIL");

        user3.setUsername("TEST_USER3");
        user3.setPassword("TEST_USER3_PASSWORD");
        user3.setEmail("TEST_USER3_EMAIL");

        userService.join(user1);
        userService.join(user2);
        userService.join(user3);

        assertThat(userService.totalUser()).isEqualTo(3);
    }

    @Test
    public void validateDuplicateUserJoinTest() {

        User newUser = new User();
        newUser.setUsername("TEST_USER1");
        newUser.setPassword("...");
        newUser.setEmail("...");

        assertThrows(IllegalStateException.class, () -> userService.join(newUser));
    }

    @Test
    public void validateDuplicateEmailJoinTest() {

        User newUser = new User();
        newUser.setUsername("...");
        newUser.setPassword("...");
        newUser.setEmail("TEST_USER1_EMAIL");

        assertThrows(IllegalStateException.class, () -> userService.join(newUser));
    }

    @Test
    public void validateDuplicateUserUpdateTest() {

        User user = new User();
        user.setId(4);
        user.setUsername("...");
        user.setPassword("...");
        user.setEmail("...");

        assertThrows(IllegalStateException.class, () -> userService.update(user));
    }

    @Test
    public void findUserTest() {

        assertThat(userService.findUser(1).getUsername()).isEqualTo("TEST_USER1");
        assertThrows(IllegalStateException.class, () -> userService.findUser(4));
    }

    @Test
    public void totalUserTest() {
        assertThat(userService.findAll().size()).isEqualTo(userService.totalUser());
    }

    @Test
    public void deactiveTest() {

        userService.deactivate(user1);
        userService.deactivate(user2);
        userService.deactivate(user3);

        userService.findAll().forEach(user ->
                assertThat(user.getIsDeleted()).isEqualTo(true));
    }
}
