package eezn.todolist.minitodo.common.enums;

import eezn.todolist.minitodo.common.constants.TodoConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    DEFAULT(TodoConstant.CATEGORY_DEFAULT),
    WORK(TodoConstant.CATEGORY_WORK),
    LIFE(TodoConstant.CATEGORY_LIFE);

    private final int priority;

//    CategoryEnum(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
}
