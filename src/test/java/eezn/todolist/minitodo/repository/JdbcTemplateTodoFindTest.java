package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.AppConfig;
import eezn.todolist.minitodo.domain.*;
import eezn.todolist.minitodo.domain.utils.CategoryEnum;
import eezn.todolist.minitodo.domain.utils.PriorityEnum;
import eezn.todolist.minitodo.domain.utils.StatusEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbcTemplateTodoFindTest {

    static TodoRepository todoRepository;
    static UserRepository userRepository;

    static User user;
    static Todo todo1, todo2, todo3;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/scheme.sql")
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AppConfig appConfig = new AppConfig(jdbcTemplate);

        userRepository = appConfig.userRepository();
        todoRepository = appConfig.todoRepository();

        user = new User();
        todo1 = new Todo();
        todo2 = new Todo();
        todo3 = new Todo();

        LocalDateTime currTime;

        user.setUsername("TEST_USER");
        user.setPassword("TEST_USER_PASSWORD");
        user.setEmail("TEST_USER_EMAIL");

        userRepository.insert(user);

        todo1.setUserId(1);
        currTime = LocalDateTime.now();
        todo1.setCreatedTime(currTime);
        todo1.setModifiedTime(currTime);
        todo1.setContent("TEST_TODO1_CONTENT");
        todo1.setIsDeleted(false);
        todo1.setCategoryId(CategoryEnum.DEFAULT.getId());
        todo1.setPriorityId(PriorityEnum.A.getId());
        todo1.setStatusId(StatusEnum.TODO.getId());

        todo2.setUserId(1);
        currTime = LocalDateTime.now();
        todo2.setCreatedTime(currTime);
        todo2.setModifiedTime(currTime);
        todo2.setContent("TEST_TODO2_CONTENT");
        todo2.setIsDeleted(false);
        todo2.setCategoryId(CategoryEnum.DEFAULT.getId());
        todo2.setPriorityId(PriorityEnum.A.getId());
        todo2.setStatusId(StatusEnum.TODO.getId());

        todo3.setUserId(1);
        currTime = LocalDateTime.now();
        todo3.setCreatedTime(currTime);
        todo3.setModifiedTime(currTime);
        todo3.setContent("TEST_TODO3_CONTENT");
        todo3.setIsDeleted(false);
        todo3.setCategoryId(CategoryEnum.DEFAULT.getId());
        todo3.setPriorityId(PriorityEnum.A.getId());
        todo3.setStatusId(StatusEnum.TODO.getId());

        todoRepository.insert(todo1);
        todoRepository.insert(todo2);
        todoRepository.insert(todo3);
    }

    @Test
    public void findByUserIdTest() {

        List<Todo> todoList = todoRepository.findByUserId(user.getId());
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(1).isEqualTo(todo.getUserId()));
    }

    @Test
    public void findByCategoryIdTest() {

        List<Todo> todoList = todoRepository.findByCategoryId(CategoryEnum.DEFAULT.getId());
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(CategoryEnum.DEFAULT.getId()).isEqualTo(todo.getCategoryId()));
    }

    @Test
    public void findByPriorityIdTest() {

        List<Todo> todoList = todoRepository.findByPriorityId(PriorityEnum.A.getId());
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(PriorityEnum.A.getId()).isEqualTo(todo.getPriorityId()));
    }

    @Test
    public void findByStatusIdTest() {

        List<Todo> todoList = todoRepository.findByStatusId(StatusEnum.TODO.getId());
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(StatusEnum.TODO.getId()).isEqualTo(todo.getStatusId()));
    }

    @Test
    public void findAllTest() {
        List<Todo> todoList = todoRepository.findAll();
        todoList.forEach(System.out::println);
    }
}
