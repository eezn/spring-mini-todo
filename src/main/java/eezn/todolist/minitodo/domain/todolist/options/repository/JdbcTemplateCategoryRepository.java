package eezn.todolist.minitodo.domain.todolist.options.repository;

import eezn.todolist.minitodo.domain.todolist.options.data.Category;
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
public class JdbcTemplateCategoryRepository implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    /** 현재 사용하지 않음 */
    @Override
    public Category insert(Category category) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo_category").usingGeneratedKeyColumns("category_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("category_name", category.getCategory())
                .addValue("category_is_deleted", category.getIsDeleted());

        int key = jdbcInsert.executeAndReturnKey(params).intValue();
        category.setId(key);
        return category;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        List<Category> result = jdbcTemplate.query(
                "select * from todo_category where category_id = ?", categoryRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from todo_category", categoryRowMapper());
    }

    private RowMapper<Category> categoryRowMapper() {
        return (rs, rowNum) -> {
            Category category = new Category();
            category.setId(rs.getInt("category_id"));
            category.setCategory(rs.getString("category_name"));
            category.setIsDeleted(rs.getBoolean("category_is_deleted"));
            return category;
        };
    }
}
