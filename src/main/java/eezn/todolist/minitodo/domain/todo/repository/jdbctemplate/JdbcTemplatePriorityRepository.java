package eezn.todolist.minitodo.domain.todo.repository.jdbctemplate;

import eezn.todolist.minitodo.domain.todo.model.Priority;
import eezn.todolist.minitodo.domain.todo.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplatePriorityRepository implements PriorityRepository {

    private final JdbcTemplate jdbcTemplate;

    /** 현재 사용하지 않음 */
    @Override
    public Priority insert(Priority priority) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo_priority").usingGeneratedKeyColumns("priority_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("priority_level", priority.getPriority())
                .addValue("priority_is_deleted", priority.getIsDeleted());

        int key = jdbcInsert.executeAndReturnKey(params).intValue();
        priority.setId(key);
        return priority;
    }

    @Override
    public Optional<Priority> findById(int id) {
        List<Priority> result = jdbcTemplate.query(
                "select * from todo_priority where priority_id = ?", priorityRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Priority> findAll() {
        return jdbcTemplate.query("select * from todo_priority", priorityRowMapper());
    }

    private RowMapper<Priority> priorityRowMapper() {
        return (rs, rowNum) -> {
            Priority priority = new Priority();
            priority.setId(rs.getInt("priority_id"));
            priority.setPriority(rs.getString("priority_level"));
            priority.setIsDeleted(rs.getBoolean("priority_is_deleted"));
            return priority;
        };
    }
}
