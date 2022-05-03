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

public class JdbcTemplateUserFindTest {

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
        user2.setIsDeleted(true);

        user3.setUsername("TEST_USER3");
        user3.setPassword("TEST_USER3_PASSWORD");
        user3.setEmail("TEST_USER3_EMAIL");
        user3.setIsDeleted(true);

        userRepository.insert(user1);
        userRepository.insert(user2);
        userRepository.insert(user3);
    }

    @Test
    public void findByIdTest() {

        userRepository.findById(user1.getId()).ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo("TEST_USER1");
            assertThat(user.getPassword()).isEqualTo("TEST_USER1_PASSWORD");
            assertThat(user.getEmail()).isEqualTo("TEST_USER1_EMAIL");
        });

        userRepository.findById(user2.getId()).ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo("TEST_USER2");
            assertThat(user.getPassword()).isEqualTo("TEST_USER2_PASSWORD");
            assertThat(user.getEmail()).isEqualTo("TEST_USER2_EMAIL");
        });

        userRepository.findById(user3.getId()).ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo("TEST_USER3");
            assertThat(user.getPassword()).isEqualTo("TEST_USER3_PASSWORD");
            assertThat(user.getEmail()).isEqualTo("TEST_USER3_EMAIL");
        });
    }

    @Test
    public void findByNameTest() {

        userRepository.findByName("TEST_USER1").ifPresent(user -> {
            assertThat(user.getPassword()).isEqualTo("TEST_USER1_PASSWORD");
            assertThat(user.getEmail()).isEqualTo("TEST_USER1_EMAIL");
        });

        userRepository.findByName("TEST_USER2").ifPresent(user -> {
            assertThat(user.getPassword()).isEqualTo("TEST_USER2_PASSWORD");
            assertThat(user.getEmail()).isEqualTo("TEST_USER2_EMAIL");
        });

        userRepository.findByName("TEST_USER3").ifPresent(user -> {
            assertThat(user.getPassword()).isEqualTo("TEST_USER3_PASSWORD");
            assertThat(user.getEmail()).isEqualTo("TEST_USER3_EMAIL");
        });
    }

    @Test
    public void findByEmailTest() {

        userRepository.findByEmail("TEST_USER1_EMAIL").ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo("TEST_USER1");
            assertThat(user.getPassword()).isEqualTo("TEST_USER1_PASSWORD");
        });

        userRepository.findByEmail("TEST_USER2_EMAIL").ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo("TEST_USER2");
            assertThat(user.getPassword()).isEqualTo("TEST_USER2_PASSWORD");
        });

        userRepository.findByEmail("TEST_USER3_EMAIL").ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo("TEST_USER3");
            assertThat(user.getPassword()).isEqualTo("TEST_USER3_PASSWORD");
        });
    }

    @Test
    public void findActiveTest() {
        userRepository.findActive().forEach(user ->
                assertThat(user.getIsDeleted()).isEqualTo(false));
    }

    @Test
    public void findDeactivatedTest() {
        userRepository.findDeactivated().forEach(user ->
                assertThat(user.getIsDeleted()).isEqualTo(true));
    }

    @Test
    public void conutUserTest() {
        System.out.println(userRepository.countUser());
    }

    @AfterAll
    static void afterAll() {
        userRepository.findAll().forEach(System.out::println);
    }
}
