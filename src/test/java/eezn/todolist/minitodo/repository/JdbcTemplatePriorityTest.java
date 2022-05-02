package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Priority;
import eezn.todolist.minitodo.domain.PriorityEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplatePriorityTest {

    @Autowired
    PriorityRepository priorityRepository;

    @Test
    public void findByIdTest() {

        Priority priorityA = priorityRepository.findById(PriorityEnum.A.getId()).get();
        Priority priorityB = priorityRepository.findById(PriorityEnum.B.getId()).get();
        Priority priorityC = priorityRepository.findById(PriorityEnum.C.getId()).get();
        Priority priorityD = priorityRepository.findById(PriorityEnum.D.getId()).get();

//        System.out.println(priorityA);
//        System.out.println(priorityB);
//        System.out.println(priorityC);
//        System.out.println(priorityD);

        assertThat("A").isEqualTo(priorityA.getPriority());
        assertThat("B").isEqualTo(priorityB.getPriority());
        assertThat("C").isEqualTo(priorityC.getPriority());
        assertThat("D").isEqualTo(priorityD.getPriority());
    }

    @Test
    public void findAllTest() {

        List<Priority> findAll = priorityRepository.findAll();

//        findAll.forEach(System.out::println);

        assertThat("A").isEqualTo(findAll.get(0).getPriority());
        assertThat("B").isEqualTo(findAll.get(1).getPriority());
        assertThat("C").isEqualTo(findAll.get(2).getPriority());
        assertThat("D").isEqualTo(findAll.get(3).getPriority());
    }
}
