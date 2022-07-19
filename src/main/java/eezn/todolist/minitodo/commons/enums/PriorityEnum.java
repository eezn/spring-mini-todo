package eezn.todolist.minitodo.commons.enums;

import eezn.todolist.minitodo.commons.constants.TodoConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PriorityEnum {

    A(TodoConstant.PRIORITY_A),
    B(TodoConstant.PRIORITY_B),
    C(TodoConstant.PRIORITY_C),
    D(TodoConstant.PRIORITY_D);

    private final int id;
}
