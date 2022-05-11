package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.AppConfig;
import eezn.todolist.minitodo.domain.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbcTemplateUserUpdateTest {

    static UserRepository userRepository;

    static User user1, user2, user3;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/scheme.sql")
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AppConfig appConfig = new AppConfig(jdbcTemplate);

        userRepository = appConfig.userRepository();

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

        userRepository.insert(user1);
        userRepository.insert(user2);
        userRepository.insert(user3);
    }

    @Test
    public void updateTest() {

        userRepository.findById(1).ifPresent(user -> {
            user.setUsername("TEST_USER1_MODIFIED");
            user.setPassword("TEST_USER1_PASSWORD_MODIFIED");
            user.setEmail("TEST_USER1_EMAIL_MODIFIED");
            userRepository.update(user);
        });

        userRepository.findByName("TEST_USER1_MODIFIED").ifPresent(user -> {
            assertThat(user.getPassword()).isEqualTo("TEST_USER1_PASSWORD_MODIFIED");
            assertThat(user.getEmail()).isEqualTo("TEST_USER1_EMAIL_MODIFIED");
        });
    }

    @Test
    public void updateDeleteFlagTest() {

        userRepository.findById(2).ifPresent(user ->
                userRepository.updateDeleteFlag(user.getId()));

        userRepository.findById(2).ifPresent(user ->
                assertThat(user.getIsDeleted()).isEqualTo(true));
    }

    @AfterAll
    static void afterAll() {
        userRepository.findAll().forEach(System.out::println);
    }
}
