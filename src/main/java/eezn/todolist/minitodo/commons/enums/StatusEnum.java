package eezn.todolist.minitodo.commons.enums;

import eezn.todolist.minitodo.commons.constants.TodoConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    TODO(TodoConstant.STATUS_TODO),
    DONE(TodoConstant.STATUS_DONE);

    private final int id;
}
