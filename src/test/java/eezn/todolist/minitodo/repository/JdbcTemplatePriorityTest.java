package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Priority;
import eezn.todolist.minitodo.repository.jdbc_template.JdbcTemplatePriorityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplatePriorityTest {

    @Autowired
    JdbcTemplatePriorityRepository repository;

    @Test
    public void findByIdTest() {

        Priority priorityA = repository.findById(1).get();
        Priority priorityB = repository.findById(2).get();
        Priority priorityC = repository.findById(3).get();
        Priority priorityD = repository.findById(4).get();

        System.out.println(priorityA);
        System.out.println(priorityB);
        System.out.println(priorityC);
        System.out.println(priorityD);

        assertThat("A").isEqualTo(priorityA.getPriority());
        assertThat("B").isEqualTo(priorityB.getPriority());
        assertThat("C").isEqualTo(priorityC.getPriority());
        assertThat("D").isEqualTo(priorityD.getPriority());
    }

    @Test
    public void findAllTest() {

        List<Priority> findAll = repository.findAll();

        for (Priority priority : findAll) {
            System.out.println(priority.toString());
        }

        assertThat("A").isEqualTo(findAll.get(0).getPriority());
        assertThat("B").isEqualTo(findAll.get(1).getPriority());
        assertThat("C").isEqualTo(findAll.get(2).getPriority());
        assertThat("D").isEqualTo(findAll.get(3).getPriority());
    }
}
