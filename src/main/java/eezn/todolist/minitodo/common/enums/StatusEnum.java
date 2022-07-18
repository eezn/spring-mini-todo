package eezn.todolist.minitodo.common.enums;

import eezn.todolist.minitodo.common.constants.TodoConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    TODO(TodoConstant.STATUS_TODO),
    DONE(TodoConstant.STATUS_DONE);

    private final int id;

//    StatusEnum(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
}
