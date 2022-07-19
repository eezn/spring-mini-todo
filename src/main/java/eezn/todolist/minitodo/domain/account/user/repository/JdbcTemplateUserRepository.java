package eezn.todolist.minitodo.domain.account.user.repository;

import eezn.todolist.minitodo.domain.account.user.data.User;
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
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User insert(User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("user_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_name", user.getUsername())
                .addValue("user_password", user.getPassword())
                .addValue("user_email", user.getEmail())
                .addValue("user_is_deleted", user.getIsDeleted());

        int key = jdbcInsert.executeAndReturnKey(params).intValue();
        user.setId(key);
        return user;
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "update users set user_name = ?, user_password = ?, user_email = ? where user_id = ?",
                user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    @Override
    public void updateDeleteFlag(Integer id) {
        jdbcTemplate.update(
                "update users set user_is_deleted = true where user_id = ?", id);
    }

    @Override
    public Optional<User> findById(Integer id) {
        List<User> result = jdbcTemplate.query(
                "select * from users where user_id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = jdbcTemplate.query(
                "select * from users where user_name = ?", userRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = jdbcTemplate.query(
                "select * from users where user_email = ?", userRowMapper(), email);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
            return jdbcTemplate.query(
                    "select * from users", userRowMapper());
    }

    @Override
    public List<User> findActive() {
        return jdbcTemplate.query(
                "select * from users where user_is_deleted is not true", userRowMapper());
    }

    @Override
    public List<User> findDeactivated() {
        return jdbcTemplate.query(
                "select * from users where user_is_deleted is true", userRowMapper());
    }

    @Override
    public int countUser() {
        return jdbcTemplate.queryForObject(
                "select COUNT(*) from users", Integer.class);
    }

    @Override
    public void clear() {
        jdbcTemplate.update("delete from users");
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("user_name"));
            user.setPassword(rs.getString("user_password"));
            user.setEmail(rs.getString("user_email"));
            user.setIsDeleted(rs.getBoolean("user_is_deleted"));
            return user;
        };
    }
}
