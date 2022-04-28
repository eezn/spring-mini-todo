package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateTodoRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateTodoUpdateTest {

    @Autowired JdbcTemplateTodoRepository todoRepository;
    @Autowired JdbcTemplateUserRepository userRepository;

    static User user;
    static Todo todo;

    @BeforeEach
    public void beforeEach() {

        user = new User();
        user.setUsername("user");
        user.setPassword("123");
        user.setEmail("123@abc.com");
        user.setIsDeleted(false);

        todo = new Todo();
        todo.setUserId(userRepository.insert(user).getId());
        todo.setCreatedTime(LocalDateTime.now());
        todo.setModifiedTime(LocalDateTime.now());
        todo.setContent("오늘의 할 일");
        todo.setIsDeleted(false);
        todo.setCategoryId(1);
        todo.setPriorityId(1);
        todo.setStatusId(1);

        todoRepository.insert(todo);

        // LocalDateTime.now()
        // LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault())
    }

    @Test
    public void updateTest() {

        Todo tempTodo = todoRepository.findById(1).get();
        tempTodo.setContent("내일의 할 일");
        todoRepository.update(tempTodo);
        todoRepository.updateDeleteFlag(todo);
        assertThat("내일의 할 일").isEqualTo(todoRepository.findById(1).get().getContent());
        assertThat(true).isEqualTo(todoRepository.findById(1).get().getIsDeleted());
    }
}
