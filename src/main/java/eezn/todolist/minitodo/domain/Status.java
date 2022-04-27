package eezn.todolist.minitodo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  `status_id`	        INT	            NOT NULL    AUTO_INCREMENT,
 * 	`status_name`	    VARCHAR(255)	NULL,
 * 	`status_is_deleted`	BOOLEAN	        NULL    DEFAULT FALSE
 */

@Getter
@Setter
@ToString
public class Status {

    private Integer id;

    private String status;
    private Boolean isDeleted;
}
