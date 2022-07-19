package eezn.todolist.minitodo.commons.enums;

import eezn.todolist.minitodo.commons.constants.TodoConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    DEFAULT(TodoConstant.CATEGORY_DEFAULT),
    WORK(TodoConstant.CATEGORY_WORK),
    LIFE(TodoConstant.CATEGORY_LIFE);

    private final int priority;
}
