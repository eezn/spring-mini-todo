package eezn.todolist.minitodo.domain.todolist.todo.data;

import lombok.Data;

@Data
public class TodoDto {

    private int todoId;
    private String content;
    private int categoryId;
    private int priorityId;
    private int statusId;
}
