package eezn.todolist.minitodo.authentication.password;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PasswordEncodeType {

    BCRYPT("bcrypt"),
    SHA256("SHA-256");

    private final String type;
}
