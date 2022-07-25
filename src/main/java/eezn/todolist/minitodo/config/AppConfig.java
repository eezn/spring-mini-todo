package eezn.todolist.minitodo.config;

import eezn.todolist.minitodo.domain.todo.repository.CategoryRepository;
import eezn.todolist.minitodo.domain.todo.repository.PriorityRepository;
import eezn.todolist.minitodo.domain.todo.repository.TodoRepository;
import eezn.todolist.minitodo.domain.todo.repository.jdbctemplate.JdbcTemplateCategoryRepository;
import eezn.todolist.minitodo.domain.todo.repository.jdbctemplate.JdbcTemplatePriorityRepository;
import eezn.todolist.minitodo.domain.todo.repository.jdbctemplate.JdbcTemplateTodoRepository;
import eezn.todolist.minitodo.domain.user.repository.JdbcTemplateUserRepository;
import eezn.todolist.minitodo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    public final JdbcTemplate jdbcTemplate;

//    @Bean
//    public UserService userService() {
//        return new UserService(userRepository(), todoRepository());
//    }
//
//    @Bean
//    public TodoService todoService() {
//        return new TodoService(userRepository(), todoRepository(), categoryRepository(), priorityRepository());
//    }

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
