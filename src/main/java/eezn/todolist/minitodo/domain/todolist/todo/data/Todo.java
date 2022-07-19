package eezn.todolist.minitodo.domain.todolist.todo.data;

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

    private Integer id;
    private Integer userId;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private String content;
    private Boolean isDeleted;

    private Integer categoryId;
    private Integer priorityId;
    private Integer statusId;
}
