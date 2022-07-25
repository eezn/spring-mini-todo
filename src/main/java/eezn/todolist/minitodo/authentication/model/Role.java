package eezn.todolist.minitodo.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private int id;
    private String name;
    private List<Privilege> privileges;
}
