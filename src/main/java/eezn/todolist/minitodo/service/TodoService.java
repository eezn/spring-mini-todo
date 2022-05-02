package eezn.todolist.minitodo.service;

import eezn.todolist.minitodo.domain.Priority;
import eezn.todolist.minitodo.domain.StatusEnum;
import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.repository.CategoryRepository;
import eezn.todolist.minitodo.repository.PriorityRepository;
import eezn.todolist.minitodo.repository.TodoRepository;
import eezn.todolist.minitodo.repository.UserRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateCategoryRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplatePriorityRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateTodoRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    public void create(Todo todo) throws IllegalStateException {
        validateUser(todo);
        validateCategory(todo);
        validatePriority(todo);
        todoRepository.insert(todo);
    }

    public void update(Todo todo) throws IllegalStateException {
        validateUser(todo);
        validateCategory(todo);
        validatePriority(todo);
        todoRepository.update(todo);
    }

    private void validateUser(Todo todo) throws IllegalStateException {
        if (userRepository.findById(todo.getUserId()).isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    private void validateCategory(Todo todo) throws IllegalStateException {
        if (categoryRepository.findById(todo.getCategoryId()).isEmpty()) {
            throw new IllegalStateException("존재하지 않는 카테고리 항목입니다.");
        }
    }

    private void validatePriority(Todo todo) throws IllegalStateException {
        if (priorityRepository.findById(todo.getPriorityId()).isEmpty()) {
            throw new IllegalStateException("존재하지 않는 우선순위 항목입니다.");
        }
    }

    public void toggleStatus(Integer todoId) throws IllegalStateException {
        validateTodo(todoId);
        if (todoRepository.findById(todoId).get().getStatusId() == StatusEnum.TODO.ordinal()) {
            todoRepository.updateStatus(todoId, StatusEnum.DONE);
        } else {
            todoRepository.updateStatus(todoId, StatusEnum.TODO);
        }
    }

    private void validateTodo(Integer todoId) throws IllegalStateException {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isEmpty() || todo.get().getIsDeleted()) {
            throw new IllegalStateException("존재하지 않는 항목입니다.");
        }
    }

    public void deactivate(Todo todo) {
        if (todoRepository.findById(todo.getId()).isPresent()) {
            todoRepository.updateDeleteFlag(todo);
        }
    }

}
