package eezn.todolist.minitodo.domain.user.model;

import lombok.Data;

@Data
public class UserDto {

    private int userId;
    private String username;
    private String password;
    private String email;
}
