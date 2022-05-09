

-- CREATE TABLE
CREATE TABLE `users` (
	`user_id`           INT             NOT NULL    AUTO_INCREMENT,
	`user_name`         VARCHAR(255)    NULL,
	`user_password`     VARCHAR(255)    NULL,
	`user_email`        VARCHAR(255)    NULL,
	`user_is_deleted`   BOOLEAN         NULL    DEFAULT FALSE
);

CREATE TABLE `todo_list` (
	`todo_id`           INT             NOT NULL    AUTO_INCREMENT,
	`user_id`           INT             NOT NULL,
	`created_time`      DATETIME        NULL,
	`modified_time`     DATETIME        NULL,
	`todo_content`      TEXT            NULL,
	`todo_is_deleted`   BOOLEAN         NULL    DEFAULT FALSE,
	`category_id`       INT             NOT NULL    DEFAULT 1,
	`priority_id`       INT             NOT NULL    DEFAULT 4,
	`status_id`         INT             NOT NULL    DEFAULT 1
);

CREATE TABLE `todo_category` (
	`category_id`           INT             NOT NULL    AUTO_INCREMENT,
	`category_name`         VARCHAR(255)    NULL,
	`category_is_deleted`   BOOLEAN         NULL    DEFAULT FALSE
);

CREATE TABLE `todo_priority` (
	`priority_id`           INT             NOT NULL    AUTO_INCREMENT,
	`priority_level`        VARCHAR(255)    NULL,
	`priority_is_deleted`   BOOLEAN         NULL    DEFAULT FALSE
);

CREATE TABLE `todo_status` (
	`status_id`             INT             NOT NULL    AUTO_INCREMENT,
	`status_name`           VARCHAR(255)    NULL,
	`status_is_deleted`     BOOLEAN         NULL    DEFAULT FALSE
);


-- PRIMARY KEY
ALTER TABLE `users`
    ADD CONSTRAINT `PK_USERS`
    PRIMARY KEY (`user_id`);

ALTER TABLE `todo_list`
    ADD CONSTRAINT `PK_TODO_LIST`
    PRIMARY KEY (`todo_id`, `user_id`);

ALTER TABLE `todo_category`
    ADD CONSTRAINT `PK_TODO_CATEGORY`
    PRIMARY KEY (`category_id`);

ALTER TABLE `todo_priority`
    ADD CONSTRAINT `PK_TODO_PRIORITY`
    PRIMARY KEY (`priority_id`);

ALTER TABLE `todo_status`
    ADD CONSTRAINT `PK_TODO_STATUS`
    PRIMARY KEY (`status_id`);


-- FOREIGN KEY
ALTER TABLE `todo_list`
    ADD CONSTRAINT `FK_users_TO_todo_list_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`);

ALTER TABLE `todo_list`
    ADD CONSTRAINT `FK_todo_category_TO_todo_list_1`
    FOREIGN KEY (`category_id`)
    REFERENCES `todo_category` (`category_id`);

ALTER TABLE `todo_list`
    ADD CONSTRAINT `FK_todo_priority_TO_todo_list_1`
    FOREIGN KEY (`priority_id`)
    REFERENCES `todo_priority` (`priority_id`);

ALTER TABLE `todo_list`
    ADD CONSTRAINT `FK_todo_status_TO_todo_list_1`
    FOREIGN KEY (`status_id`)
    REFERENCES `todo_status` (`status_id`);


-- INIT TABLE INSTANCE
INSERT INTO `todo_category` (category_name) VALUES ('default');
INSERT INTO `todo_category` (category_name) VALUES ('work');
INSERT INTO `todo_category` (category_name) VALUES ('life');

INSERT INTO `todo_priority` (priority_level) VALUES ('A');
INSERT INTO `todo_priority` (priority_level) VALUES ('B');
INSERT INTO `todo_priority` (priority_level) VALUES ('C');
INSERT INTO `todo_priority` (priority_level) VALUES ('D');

INSERT INTO `todo_status` (status_name) VALUES ('todo');
INSERT INTO `todo_status` (status_name) VALUES ('done');

-- DUMMY USER
INSERT INTO `users` (user_name, user_password, user_email)
    VALUES ('JIN-LEE', '123', 'JIN-LEE@student.42seoul.kr');
