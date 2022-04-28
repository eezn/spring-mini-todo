package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Status;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateStatusTest {

    @Autowired
    JdbcTemplateStatusRepository repository;

    @Test
    public void findByIdTest() {

        Status statusDone = repository.findById(1).get();
        Status statusTodo = repository.findById(2).get();

        System.out.println(statusDone);
        System.out.println(statusTodo);

        assertThat("done").isEqualTo(statusDone.getStatus());
        assertThat("todo").isEqualTo(statusTodo.getStatus());
    }

    @Test
    public void findAllTest() {

        List<Status> findAll = repository.findAll();

        for (Status status : findAll) {
            System.out.println(status.toString());
        }

        assertThat("done").isEqualTo(findAll.get(0).getStatus());
        assertThat("todo").isEqualTo(findAll.get(1).getStatus());
    }
}
