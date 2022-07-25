package eezn.todolist.minitodo.domain.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eezn.todolist.minitodo.authentication.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 *  `user_id`           INT             NOT NULL AUTO_INCREMENT,
 * 	`user_name`	        VARCHAR(255)    NULL,
 * 	`user_password`	    VARCHAR(255)    NULL,
 * 	`user_email`	    VARCHAR(255)    NULL,
 * 	`user_is_deleted`	BOOLEAN         NULL    DEFAULT FALSE
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;
    private Boolean isDeleted;

    private List<Role> roles;
    private Collection<SimpleGrantedAuthority> authorities;
}
