package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.repository.jdbc_template.JdbcTemplateUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JdbcTemplateUserUpdateTest {

    @Autowired
    JdbcTemplateUserRepository repository;

    static User user1;
    static User user2;
    static User user3;

    @BeforeEach
    public void beforeEach() {

        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("123");
        user1.setEmail("123@abc.com");
        user1.setIsDeleted(false);

        user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("456");
        user2.setEmail("456@abc.com");
        user2.setIsDeleted(false);

        user3 = new User();
        user3.setUsername("user3");
        user3.setPassword("789");
        user3.setEmail("789@abc.com");
        user3.setIsDeleted(true);

        repository.insert(user1);
        repository.insert(user2);
        repository.insert(user3);
    }

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void updateTest() {

        List<User> users = repository.findAll();
        users.forEach(System.out::println);

        users.get(0).setUsername("new_name");
        users.get(1).setPassword("new_password");
        users.get(2).setEmail("new_email");

        repository.update(users.get(0));
        repository.update(users.get(1));
        repository.update(users.get(2));

        repository.updateDeleteFlag(users.get(0));
        repository.updateDeleteFlag(users.get(1));

        List<User> updatedUsers = repository.findAll();
        updatedUsers.forEach(System.out::println);

        Assertions.assertThat("new_name").isEqualTo(updatedUsers.get(0).getUsername());
        Assertions.assertThat("new_password").isEqualTo(updatedUsers.get(1).getPassword());
        Assertions.assertThat("new_email").isEqualTo(updatedUsers.get(2).getEmail());

        updatedUsers.forEach(user -> Assertions.assertThat(true).isEqualTo(user.getIsDeleted()));
    }

}
