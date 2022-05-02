package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Status;
import eezn.todolist.minitodo.domain.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateStatusTest {

    @Autowired
    StatusRepository statusRepository;

    @Test
    public void findByIdTest() {

        Status statusDone = statusRepository.findById(StatusEnum.TODO.getId()).get();
        Status statusTodo = statusRepository.findById(StatusEnum.DONE.getId()).get();

//        System.out.println(statusDone);
//        System.out.println(statusTodo);

        assertThat("todo").isEqualTo(statusDone.getStatus());
        assertThat("done").isEqualTo(statusTodo.getStatus());
    }

    @Test
    public void findAllTest() {

        List<Status> findAll = statusRepository.findAll();

//        findAll.forEach(System.out::println);

        assertThat("todo").isEqualTo(findAll.get(0).getStatus());
        assertThat("done").isEqualTo(findAll.get(1).getStatus());
    }
}
