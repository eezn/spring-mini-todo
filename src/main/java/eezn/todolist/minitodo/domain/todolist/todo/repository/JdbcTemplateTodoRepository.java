package eezn.todolist.minitodo.domain.todolist.todo.repository;

import eezn.todolist.minitodo.commons.enums.StatusEnum;
import eezn.todolist.minitodo.domain.todolist.todo.data.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateTodoRepository implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Todo insert(Todo todo) {

        LocalDateTime currTime = LocalDateTime.now();

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo_list").usingGeneratedKeyColumns("todo_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", todo.getUserId())
                .addValue("created_time", currTime)
                .addValue("modified_time", currTime)
                .addValue("todo_content", todo.getContent())
                .addValue("todo_is_deleted", false)
                .addValue("category_id", todo.getCategoryId())
                .addValue("priority_id", todo.getPriorityId())
                .addValue("status_id", StatusEnum.TODO.getId());

        int key = jdbcInsert.executeAndReturnKey(params).intValue();
        todo.setId(key);
        return todo;
    }

    @Override
    public void update(Todo todo) {
        jdbcTemplate.update(
                "update todo_list set " +
                        "modified_time = ?, " +
                        "todo_content = ?, " +
                        "todo_is_deleted = ?, " +
                        "category_id = ?, " +
                        "priority_id = ?, " +
                        "status_id = ? " +
                        "where todo_id = ?",
                LocalDateTime.now(),
                todo.getContent(),
                todo.getIsDeleted(),
                todo.getCategoryId(),
                todo.getPriorityId(),
                todo.getStatusId(),
                todo.getId()
        );
    }

    @Override
    public void updateStatus(Integer id, StatusEnum status) {
        jdbcTemplate.update("update todo_list set modified_time = ?, status_id = ? where todo_id = ?",
                LocalDateTime.now(), status.getId(), id);
    }

    @Override
    public void updateDeleteFlag(Integer id) {
        jdbcTemplate.update(
                "update todo_list set modified_time = ?, todo_is_deleted = true where todo_id = ?",
                LocalDateTime.now(), id);
    }

    @Override
    public Optional<Todo> findById(Integer id) {
        List<Todo> result = jdbcTemplate.query(
                "select * from todo_list where todo_id = ?", todoRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Todo> findByUserId(Integer userId) {
        List<Todo> result = jdbcTemplate.query(
                "select * from todo_list where user_id = ?", todoRowMapper(), userId);
        return result;
    }

    @Override
    public List<Todo> findByCategoryId(Integer categoryId) {
        List<Todo> result = jdbcTemplate.query(
                "select * from todo_list where category_id = ?", todoRowMapper(), categoryId);
        return result;
    }

    @Override
    public List<Todo> findByPriorityId(Integer priorityId) {
        List<Todo> result = jdbcTemplate.query(
                "select * from todo_list where priority_id = ?", todoRowMapper(), priorityId);
        return result;
    }

    @Override
    public List<Todo> findByStatusId(Integer statusId) {
        List<Todo> result = jdbcTemplate.query(
                "select * from todo_list where status_id = ?", todoRowMapper(), statusId);
        return result;
    }

    @Override
    public List<Todo> findAll() {
        return jdbcTemplate.query(
                "select * from todo_list", todoRowMapper());
    }

    @Override
    public List<Todo> findActive() {
        return jdbcTemplate.query(
                "select * from todo_list where todo_is_deleted is not true", todoRowMapper());
    }

    @Override
    public List<Todo> findDeactivated() {
        return jdbcTemplate.query(
                "select * from todo_list where todo_is_deleted is true", todoRowMapper());
    }

    @Override
    public void clear() {
        jdbcTemplate.update("delete from todo_list");
    }

    private RowMapper<Todo> todoRowMapper() {
        return (rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setId(rs.getInt("todo_id"));
            todo.setUserId(rs.getInt("user_id"));
            todo.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
            todo.setModifiedTime(rs.getTimestamp("modified_time").toLocalDateTime());
            todo.setContent(rs.getString("todo_content"));
            todo.setIsDeleted(rs.getBoolean("todo_is_deleted"));
            todo.setCategoryId(rs.getInt("category_id"));
            todo.setPriorityId(rs.getInt("priority_id"));
            todo.setStatusId(rs.getInt("status_id"));
            return todo;
        };
    }
}
