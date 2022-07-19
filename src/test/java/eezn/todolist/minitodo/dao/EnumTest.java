package eezn.todolist.minitodo.dao;

import eezn.todolist.minitodo.commons.enums.CategoryEnum;
import eezn.todolist.minitodo.commons.enums.PriorityEnum;
import eezn.todolist.minitodo.commons.enums.StatusEnum;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    public void categoryEnumTest() {

        CategoryEnum categoryDefault = CategoryEnum.DEFAULT;
        CategoryEnum categoryWork = CategoryEnum.WORK;
        CategoryEnum categoryLife = CategoryEnum.LIFE;

        System.out.println(categoryDefault.getPriority());
        System.out.println(categoryWork.getPriority());
        System.out.println(categoryLife.getPriority());
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
