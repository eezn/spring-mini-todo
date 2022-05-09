package eezn.todolist.minitodo.controller.dto;

import lombok.Data;

@Data
public class TodoDto {

    private Integer todoId;
    private String content;
    private Integer categoryId;
    private Integer priorityId;
    private Integer statusId;
}
