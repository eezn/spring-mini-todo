package eezn.todolist.minitodo.domain.user.data;

import lombok.Data;

@Data
public class UserDto {

    private int userId;
    private String username;
    private String password;
    private String email;
}
