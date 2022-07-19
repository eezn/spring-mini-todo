package eezn.todolist.minitodo.domain.account.user.data;

import lombok.Data;

@Data
public class UserDto {

    private Integer userId;
    private String username;
    private String password;
    private String email;
}
