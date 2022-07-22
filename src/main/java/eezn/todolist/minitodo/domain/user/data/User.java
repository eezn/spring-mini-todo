package eezn.todolist.minitodo.domain.user.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  `user_id`           INT             NOT NULL AUTO_INCREMENT,
 * 	`user_name`	        VARCHAR(255)    NULL,
 * 	`user_password`	    VARCHAR(255)    NULL,
 * 	`user_email`	    VARCHAR(255)    NULL,
 * 	`user_is_deleted`	BOOLEAN         NULL    DEFAULT FALSE
 */

@Getter
@Setter
@ToString
public class User {

    private int id;

    private String username;
    private String password;
    private String email;
    private Boolean isDeleted;
}
