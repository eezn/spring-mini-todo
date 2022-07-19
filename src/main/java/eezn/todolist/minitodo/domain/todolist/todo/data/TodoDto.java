package eezn.todolist.minitodo.domain.todolist.todo.data;

import lombok.Data;

@Data
public class TodoDto {

    private Integer todoId;
    private String content;
    private Integer categoryId;
    private Integer priorityId;
    private Integer statusId;
}
