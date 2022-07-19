-- DUMMY USER
INSERT INTO `users` (user_name, user_password, user_email)
    VALUES ('JIN-LEE', '123', 'JIN-LEE@student.42seoul.kr');

INSERT INTO `users` (user_name, user_password, user_email)
    VALUES ('MINKANG', '123', 'MINKANG@student.42seoul.kr');

-- DUMMY TODO
INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (1, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_01', false, 2, 1, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (2, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_02', false, 2, 2, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (3, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_03', false, 2, 3, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (4, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_04', false, 2, 4, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (5, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_05', false, 3, 1, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (6, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_06', false, 3, 2, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (7, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_07', false, 3, 3, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (8, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_08', false, 3, 4, 1);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (9, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_09', false, 2, 1, 2);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (10, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_10', false, 2, 2, 2);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (11, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_11', false, 3, 3, 2);

INSERT INTO `todo_list` (todo_id, user_id, `created_time`, `modified_time`, todo_content, todo_is_deleted, category_id, priority_id, status_id)
    VALUES (12, 1, '2022-05-13 00:00:00.000000', '2022-05-13 00:00:00.000000', 'DUMMY_DATA_12', false, 3, 4, 2);
