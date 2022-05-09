package eezn.todolist.minitodo.controller.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer userId;
    private String username;
    private String password;
    private String email;
}
