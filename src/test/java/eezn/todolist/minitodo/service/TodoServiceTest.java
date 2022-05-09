package eezn.todolist.minitodo.service;

import eezn.todolist.minitodo.AppConfig;
import eezn.todolist.minitodo.domain.*;
import eezn.todolist.minitodo.domain.utils.CategoryEnum;
import eezn.todolist.minitodo.domain.utils.PriorityEnum;
import eezn.todolist.minitodo.domain.utils.StatusEnum;
import eezn.todolist.minitodo.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoServiceTest {

    static UserRepository userRepository;
    static TodoService todoService;

    static User user1, user2;
    static Todo todo1, todo2, todo3, todo4, todo5;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/scheme.sql")
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AppConfig appConfig = new AppConfig(jdbcTemplate);

        userRepository = appConfig.userRepository();
        todoService = appConfig.todoService();

        user1 = new User();
        user2 = new User();

        todo1 = new Todo();
        todo2 = new Todo();
        todo3 = new Todo();
        todo4 = new Todo();
        todo5 = new Todo();

        LocalDateTime currTime;

        user1.setUsername("TEST_USER1");
        user1.setPassword("TEST_USER1_PASSWORD");
        user1.setEmail("TEST_USER1_EMAIL");

        user2.setUsername("TEST_USER2");
        user2.setPassword("TEST_USER2_PASSWORD");
        user2.setEmail("TEST_USER2_EMAIL");

        userRepository.insert(user1);
        userRepository.insert(user2);

        todo1.setUserId(user1.getId());
        currTime = LocalDateTime.now();
        todo1.setCreatedTime(currTime);
        todo1.setModifiedTime(currTime);
        todo1.setContent("TEST_TODO1_CONTENT");
        todo1.setIsDeleted(false);
        todo1.setCategoryId(CategoryEnum.WORK.getId());
        todo1.setPriorityId(PriorityEnum.A.getId());
        todo1.setStatusId(StatusEnum.TODO.getId());

        todo2.setUserId(user1.getId());
        currTime = LocalDateTime.now();
        todo2.setCreatedTime(currTime);
        todo2.setModifiedTime(currTime);
        todo2.setContent("TEST_TODO2_CONTENT");
        todo2.setIsDeleted(false);
        todo2.setCategoryId(CategoryEnum.WORK.getId());
        todo2.setPriorityId(PriorityEnum.B.getId());
        todo2.setStatusId(StatusEnum.TODO.getId());

        todo3.setUserId(user1.getId());
        currTime = LocalDateTime.now();
        todo3.setCreatedTime(currTime);
        todo3.setModifiedTime(currTime);
        todo3.setContent("TEST_TODO3_CONTENT");
        todo3.setIsDeleted(false);
        todo3.setCategoryId(CategoryEnum.LIFE.getId());
        todo3.setPriorityId(PriorityEnum.C.getId());
        todo3.setStatusId(StatusEnum.TODO.getId());

        todo4.setUserId(user2.getId());
        currTime = LocalDateTime.now();
        todo4.setCreatedTime(currTime);
        todo4.setModifiedTime(currTime);
        todo4.setContent("TEST_TODO4_CONTENT");
        todo4.setIsDeleted(false);
        todo4.setCategoryId(CategoryEnum.WORK.getId());
        todo4.setPriorityId(PriorityEnum.A.getId());
        todo4.setStatusId(StatusEnum.TODO.getId());

        todo5.setUserId(user2.getId());
        currTime = LocalDateTime.now();
        todo5.setCreatedTime(currTime);
        todo5.setModifiedTime(currTime);
        todo5.setContent("TEST_TODO5_CONTENT");
        todo5.setIsDeleted(false);
        todo5.setCategoryId(CategoryEnum.LIFE.getId());
        todo5.setPriorityId(PriorityEnum.B.getId());
        todo5.setStatusId(StatusEnum.TODO.getId());

        todoService.create(todo1);
        todoService.create(todo2);
        todoService.create(todo3);
        todoService.create(todo4);
        todoService.create(todo5);
    }

    @Test
    public void findByUserIdTest() {

        todoService.findByUserId(1).forEach(todo ->
                assertThat(todo.getUserId()).isEqualTo(1));

        todoService.findByUserId(2).forEach(todo ->
                assertThat(todo.getUserId()).isEqualTo(2));
    }

    @Test
    public void findByCategoryIdTest() {

        todoService.findByCategoryId(1, CategoryEnum.WORK.getId()).forEach(todo ->
                assertThat(todo.getCategoryId()).isEqualTo(CategoryEnum.WORK.getId()));

        todoService.findByCategoryId(2, CategoryEnum.LIFE.getId()).forEach(todo ->
                assertThat(todo.getCategoryId()).isEqualTo(CategoryEnum.LIFE.getId()));
    }

    @Test
    public void findByStatusIdTest() {

        todoService.findByStatusId(1, StatusEnum.TODO.getId()).forEach(todo ->
                assertThat(todo.getStatusId()).isEqualTo(StatusEnum.TODO.getId()));

        todoService.findByStatusId(2, StatusEnum.DONE.getId()).forEach(todo ->
                assertThat(todo.getStatusId()).isEqualTo(StatusEnum.DONE.getId()));
    }

    @Test
    public void toggleStatusTest() {

        // status == TODO
        assertThat(todoService.findById(todo1.getId()).getStatusId())
                .isEqualTo(StatusEnum.TODO.getId());

        // TODO -> DONE
        todoService.toggleStatus(todo1.getId());

        // status == DONE
        assertThat(todoService.findById(todo1.getId()).getStatusId())
                .isEqualTo(StatusEnum.DONE.getId());

        // TODO -> DONE
        todoService.toggleStatus(todo1.getId());

        // status == TODO
        assertThat(todoService.findById(todo1.getId()).getStatusId())
                .isEqualTo(StatusEnum.TODO.getId());
    }

    @Test
    public void deactivateTest() {

        Todo todo = new Todo();

        todo.setUserId(user2.getId());
        LocalDateTime currTime = LocalDateTime.now();
        todo.setCreatedTime(currTime);
        todo.setModifiedTime(currTime);
        todo.setContent("DEACTIVATE_TEST_TODO");
        todo.setIsDeleted(false);
        todo.setCategoryId(CategoryEnum.LIFE.getId());
        todo.setPriorityId(PriorityEnum.B.getId());
        todo.setStatusId(StatusEnum.TODO.getId());

        todoService.create(todo);
        todoService.deactivate(todo.getId());
        assertThrows(IllegalStateException.class, () -> todoService.findById(todo.getId()));
    }
}
