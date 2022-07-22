package eezn.todolist.minitodo.domain.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  `category_id`	        INT	            NOT NULL    AUTO_INCREMENT,
 * 	`category_name`	        VARCHAR(255)	NULL,
 * 	`category_is_deleted`	BOOLEAN	        NULL	DEFAULT FALSE
 */

@Getter
@Setter
@ToString
public class Category {

    private int id;

    private String category;
    private Boolean isDeleted;
}
