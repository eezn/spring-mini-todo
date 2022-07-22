package eezn.todolist.minitodo.domain.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  `status_id`	            INT	            NOT NULL AUTO_INCREMENT,
 * 	`status_name`	        VARCHAR(255)	NULL,
 * 	`status_is_deleted`	    BOOLEAN	        NULL	DEFAULT FALSE
 */

@Getter
@Setter
@ToString
public class Priority {

    private int id;

    private String priority;
    private Boolean isDeleted;
}
