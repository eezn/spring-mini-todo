package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateTodoRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateTodoFindTest {

    @Autowired JdbcTemplateTodoRepository todoRepository;
    @Autowired JdbcTemplateUserRepository userRepository;

    static User user;
    static Todo todo1;
    static Todo todo2;
    static Todo todo3;

    @BeforeEach
    public void beforeEach() {

        user = new User();
        user.setUsername("user");
        user.setPassword("123");
        user.setEmail("123@abc.com");
        user.setIsDeleted(false);

        userRepository.insert(user);

        todo1 = new Todo();
        todo1.setUserId(userRepository.findById(1).get().getId());
        todo1.setCreatedTime(LocalDateTime.now());
        todo1.setModifiedTime(LocalDateTime.now());
        todo1.setContent("오늘의 할 일");
        todo1.setIsDeleted(false);
        todo1.setCategoryId(1);
        todo1.setPriorityId(1);
        todo1.setStatusId(2);

        todo2 = new Todo();
        todo2.setUserId(userRepository.findById(1).get().getId());
        todo2.setCreatedTime(LocalDateTime.now());
        todo2.setModifiedTime(LocalDateTime.now());
        todo2.setContent("내일의 할 일");
        todo2.setIsDeleted(false);
        todo2.setCategoryId(1);
        todo2.setPriorityId(2);
        todo2.setStatusId(1);

        todo3 = new Todo();
        todo3.setUserId(userRepository.findById(1).get().getId());
        todo3.setCreatedTime(LocalDateTime.now());
        todo3.setModifiedTime(LocalDateTime.now());
        todo3.setContent("사야할 것");
        todo3.setIsDeleted(false);
        todo3.setCategoryId(2);
        todo3.setPriorityId(4);
        todo3.setStatusId(1);

        todoRepository.insert(todo1);
        todoRepository.insert(todo2);
        todoRepository.insert(todo3);
    }

    @AfterEach
    public void afterEach() {
        todoRepository.clear();
    }

    @Test
    public void findByUserIdTest() {

        List<Todo> todoList = todoRepository.findByUserId(1);
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(1).isEqualTo(todo.getUserId()));
    }

    @Test
    public void findByCategoryIdTest() {

        List<Todo> todoList = todoRepository.findByCategoryId(1);
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(1).isEqualTo(todo.getCategoryId()));
    }

    @Test
    public void findByPriorityIdTest() {

        List<Todo> todoList = todoRepository.findByPriorityId(4);
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(4).isEqualTo(todo.getPriorityId()));
    }

    @Test
    public void findByStatusIdTest() {

        List<Todo> todoList = todoRepository.findByStatusId(1);
        todoList.forEach(System.out::println);
        todoList.forEach(todo -> assertThat(1).isEqualTo(todo.getStatusId()));
    }

    @Test
    public void findAllTest() {
        List<Todo> todoList = todoRepository.findAll();
        todoList.forEach(System.out::println);
    }
}
