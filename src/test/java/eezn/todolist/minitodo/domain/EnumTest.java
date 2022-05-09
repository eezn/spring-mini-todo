package eezn.todolist.minitodo.domain;

import eezn.todolist.minitodo.domain.utils.CategoryEnum;
import eezn.todolist.minitodo.domain.utils.PriorityEnum;
import eezn.todolist.minitodo.domain.utils.StatusEnum;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    public void categoryEnumTest() {

        CategoryEnum categoryDefault = CategoryEnum.DEFAULT;
        CategoryEnum categoryWork = CategoryEnum.WORK;
        CategoryEnum categoryLife = CategoryEnum.LIFE;

        System.out.println(categoryDefault.getId());
        System.out.println(categoryWork.getId());
        System.out.println(categoryLife.getId());
    }

    @Test
    public void priorityEnumTest() {

        PriorityEnum priorityA = PriorityEnum.A;
        PriorityEnum priorityB = PriorityEnum.B;
        PriorityEnum priorityC = PriorityEnum.C;
        PriorityEnum priorityD = PriorityEnum.D;

        System.out.println(priorityA.getId());
        System.out.println(priorityB.getId());
        System.out.println(priorityC.getId());
        System.out.println(priorityD.getId());
    }

    @Test
    public void statusEnumTest() {

        StatusEnum statusTodo = StatusEnum.TODO;
        StatusEnum statusDone = StatusEnum.DONE;

        System.out.println(statusTodo.getId());
        System.out.println(statusDone.getId());
    }
}
