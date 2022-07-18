package eezn.todolist.minitodo.service;

import eezn.todolist.minitodo.common.enums.StatusEnum;
import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.repository.CategoryRepository;
import eezn.todolist.minitodo.repository.PriorityRepository;
import eezn.todolist.minitodo.repository.TodoRepository;
import eezn.todolist.minitodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    public void create(Todo todo) throws IllegalStateException {
        validateContent(todo);
        validateUser(todo.getUserId());
        validateCategory(todo.getCategoryId());
        validatePriority(todo.getPriorityId());
        todoRepository.insert(todo);
    }

    public void update(Todo todo) throws IllegalStateException {
        validateContent(todo);
        validateUser(todo.getUserId());
        validateCategory(todo.getCategoryId());
        validatePriority(todo.getPriorityId());
        todoRepository.update(todo);
    }

    public void toggleStatus(Integer todoId) throws IllegalStateException {
        validateTodo(todoId);
        if (todoRepository.findById(todoId).get().getStatusId() == StatusEnum.TODO.getId()) {
            todoRepository.updateStatus(todoId, StatusEnum.DONE);
        } else {
            todoRepository.updateStatus(todoId, StatusEnum.TODO);
        }
    }

    public void deactivate(Integer todoId) {
        if (todoRepository.findById(todoId).isPresent()) {
            todoRepository.updateDeleteFlag(todoId);
        }
    }

    public Todo findById(Integer todoId) {
        validateTodo(todoId);
        return todoRepository.findById(todoId).get();
    }

    /** userId */
    public List<Todo> findByUserId(Integer userId) {
        validateUser(userId);
        return todoRepository.findByUserId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false))
                .collect(Collectors.toList());
    }

    /** userId, statusId */
    public List<Todo> findByStatusId(Integer userId, Integer statusId) {
        validateUser(userId);
        validatePriority(statusId);
        return todoRepository.findByUserId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false)
                        && todo.getStatusId().equals(statusId))
                .sorted((o1, o2) -> o1.getCategoryId() == o2.getCategoryId()
                        ? Integer.compare(o1.getPriorityId(), o2.getPriorityId())
                        : Integer.compare(o1.getCategoryId(), o2.getCategoryId()))
                .collect(Collectors.toList());
    }

    /** userId, categoryId */
    public List<Todo> findByCategoryId(Integer userId, Integer categoryId) {
        validateUser(userId);
        validateCategory(categoryId);
        return todoRepository.findByUserId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false)
                        && todo.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    private void validateUser(Integer userId) throws IllegalStateException {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    private void validateTodo(Integer todoId) throws IllegalStateException {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isEmpty() || todo.get().getIsDeleted()) {
            throw new IllegalStateException("존재하지 않는 항목입니다.");
        }
    }

    private void validateContent(Todo todo) throws IllegalStateException {
        if (todo.getContent().isEmpty()) {
            throw new IllegalStateException("빈 문자열입니다.");
        }
    }

    private void validateCategory(Integer categoryId) throws IllegalStateException {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new IllegalStateException("존재하지 않는 카테고리 항목입니다.");
        }
    }

    private void validatePriority(Integer priorityId) throws IllegalStateException {
        if (priorityRepository.findById(priorityId).isEmpty()) {
            throw new IllegalStateException("존재하지 않는 우선순위 항목입니다.");
        }
    }
}
