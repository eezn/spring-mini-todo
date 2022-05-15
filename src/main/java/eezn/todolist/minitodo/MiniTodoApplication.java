package eezn.todolist.minitodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application.properties" })
public class MiniTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniTodoApplication.class, args);
	}

}
