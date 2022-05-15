package eezn.todolist.minitodo.bean;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class BeanTest {
//
//    AnnotationConfigApplicationContext ac
//            = new AnnotationConfigApplicationContext(AppConfigBeanTest.class);
//
//    @Test
//    @DisplayName("All Bean")
//    void findAllBean() {
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("name = " + beanDefinitionName + " object = " + bean);
//        }
//    }
//
//    @Test
//    @DisplayName("Application Bean")
//    void findApplicationBean() {
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
//
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
//                Object bean = ac.getBean(beanDefinitionName);
//                System.out.println("name = " + beanDefinitionName + " object = " + bean);
//            }
//        }
//    }
//
//    @Configuration
//    static class AppConfigBeanTest {
//
//        DataSource dataSource = new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:db/schema.sql")
//                .build();
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//
//        @Bean
//        public UserService userService() {
//            return new UserService(userRepository(), todoRepository());
//        }
//
//        @Bean
//        public TodoService todoService() {
//            return new TodoService(userRepository(), todoRepository(), categoryRepository(), priorityRepository());
//        }
//
//        @Bean
//        public UserRepository userRepository() {
//            return new JdbcTemplateUserRepository(jdbcTemplate);
//        }
//
//        @Bean
//        public TodoRepository todoRepository() {
//            return new JdbcTemplateTodoRepository(jdbcTemplate);
//        }
//
//        @Bean
//        public CategoryRepository categoryRepository() {
//            return new JdbcTemplateCategoryRepository(jdbcTemplate);
//        }
//
//        @Bean
//        public PriorityRepository priorityRepository() {
//            return new JdbcTemplatePriorityRepository(jdbcTemplate);
//        }
//    }
}
