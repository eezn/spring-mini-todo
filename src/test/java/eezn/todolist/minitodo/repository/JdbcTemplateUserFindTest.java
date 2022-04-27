package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.repository.jdbc_template.JdbcTemplateUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateUserFindTest {

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
    public void findByIdTest() {

//        Optional<User> userOpt = repository.findById(1);
//        User getUser = userOpt.orElse(new User());

        List<User> findAll = repository.findAll();
        findAll.forEach(user -> {
            Integer id = user.getId();
            Optional<User> find = repository.findById(id);
            System.out.println(find.get());
            assertThat(id).isEqualTo(find.get().getId());
        });
    }

    @Test
    public void findByNameTest() {

        User findByName1 = repository.findByName("user1").get();
        User findByName2 = repository.findByName("user2").get();
        assertThat("user1").isEqualTo(findByName1.getUsername());
        assertThat("user2").isEqualTo(findByName2.getUsername());
    }

    @Test
    public void findByEmailTest() {
        User findByEmail1 = repository.findByEmail("123@abc.com").get();
        User findByEmail2 = repository.findByEmail("456@abc.com").get();
        assertThat("123@abc.com").isEqualTo(findByEmail1.getEmail());
        assertThat("456@abc.com").isEqualTo(findByEmail2.getEmail());
    }

    @Test
    public void findAllTest() {

        List<User> findAll = repository.findAll();
        findAll.forEach(System.out::println);
        assertThat("123@abc.com").isEqualTo(findAll.get(0).getEmail());
        assertThat("456@abc.com").isEqualTo(findAll.get(1).getEmail());
        assertThat("789@abc.com").isEqualTo(findAll.get(2).getEmail());
    }

    @Test
    public void findActiveTest() {

        List<User> findActive = repository.findActive();
        findActive.forEach(System.out::println);
        assertThat("user1").isEqualTo(findActive.get(0).getUsername());
        assertThat("user2").isEqualTo(findActive.get(1).getUsername());
    }

    @Test
    public void findDeactivatedTest() {

        List<User> findDeactivated = repository.findDeactivated();
        findDeactivated.forEach(System.out::println);
        assertThat("user3").isEqualTo(findDeactivated.get(0).getUsername());
    }
}
