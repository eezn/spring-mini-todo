package eezn.todolist.minitodo.controller.form;

import lombok.Data;

@Data
public class TodoForm {

    private String content;
    private Integer categoryId;
    private Integer priorityId;
}
