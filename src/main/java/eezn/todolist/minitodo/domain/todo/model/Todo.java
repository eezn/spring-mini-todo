package eezn.todolist.minitodo.domain.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *  `todo_id`           INT             NOT NULL    AUTO_INCREMENT,
 * 	`user_id`           INT             NOT NULL,
 * 	`created_time`      DATETIME        NULL,
 * 	`modified_time`     DATETIME        NULL,
 * 	`todo_content`      TEXT            NULL,
 * 	`todo_is_deleted`   BOOLEAN         NULL    DEFAULT FALSE,
 * 	`category_id`       INT             NOT NULL,
 * 	`priority_id`       INT             NOT NULL,
 * 	`status_id`         INT             NOT NULL
 */

@Getter
@Setter
@ToString
public class Todo {

    private int id;
    private int userId;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private String content;
    private Boolean isDeleted;

    private int categoryId;
    private int priorityId;
    private int statusId;
}
