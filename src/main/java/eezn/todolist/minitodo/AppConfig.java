package eezn.todolist.minitodo;

import eezn.todolist.minitodo.repository.CategoryRepository;
import eezn.todolist.minitodo.repository.PriorityRepository;
import eezn.todolist.minitodo.repository.TodoRepository;
import eezn.todolist.minitodo.repository.UserRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateCategoryRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplatePriorityRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateTodoRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateUserRepository;
import eezn.todolist.minitodo.service.TodoService;
import eezn.todolist.minitodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    public final JdbcTemplate jdbcTemplate;

    @Bean
    public UserService userService() {
        return new UserService(userRepository(), todoRepository());
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(userRepository(), todoRepository(), categoryRepository(), priorityRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcTemplateUserRepository(jdbcTemplate);
    }

    @Bean
    public TodoRepository todoRepository() {
        return new JdbcTemplateTodoRepository(jdbcTemplate);
    }

    @Bean
    public CategoryRepository categoryRepository() {
        return new JdbcTemplateCategoryRepository(jdbcTemplate);
    }

    @Bean
    public PriorityRepository priorityRepository() {
        return new JdbcTemplatePriorityRepository(jdbcTemplate);
    }
}
