package eezn.todolist.minitodo.domain.todo.repository.jdbctemplate;

import eezn.todolist.minitodo.domain.todo.model.Status;
import eezn.todolist.minitodo.domain.todo.repository.StatusRepository;
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
public class JdbcTemplateStatusRepository implements StatusRepository {

    private final JdbcTemplate jdbcTemplate;

    /** 현재 사용하지 않음 */
    @Override
    public Status insert(Status status) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo_status").usingGeneratedKeyColumns("status_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("status_name", status.getStatus())
                .addValue("status_is_deleted", status.getIsDeleted());

        int key = jdbcInsert.executeAndReturnKey(params).intValue();
        status.setId(key);
        return status;
    }

    @Override
    public Optional<Status> findById(int id) {
        List<Status> result = jdbcTemplate.query(
                "select * from todo_status where status_id = ?", statusRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Status> findAll() {
        return jdbcTemplate.query("select * from todo_status", statusRowMapper());
    }

    private RowMapper<Status> statusRowMapper() {
        return (rs, rowNum) -> {
            Status status = new Status();
            status.setId(rs.getInt("status_id"));
            status.setStatus(rs.getString("status_name"));
            status.setIsDeleted(rs.getBoolean("status_is_deleted"));
            return status;
        };
    }
}
