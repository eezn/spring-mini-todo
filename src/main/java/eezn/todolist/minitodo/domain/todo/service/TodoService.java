package eezn.todolist.minitodo.domain.todo.service;

import eezn.todolist.minitodo.commons.enums.StatusEnum;
import eezn.todolist.minitodo.domain.todo.model.Category;
import eezn.todolist.minitodo.domain.todo.model.Priority;
import eezn.todolist.minitodo.domain.todo.repository.CategoryRepository;
import eezn.todolist.minitodo.domain.todo.repository.PriorityRepository;
import eezn.todolist.minitodo.domain.user.data.User;
import eezn.todolist.minitodo.domain.todo.model.Todo;
import eezn.todolist.minitodo.domain.todo.repository.TodoRepository;
import eezn.todolist.minitodo.domain.user.repository.UserRepository;
import eezn.todolist.minitodo.domain.todo.service.comparator.PriorityComparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    public void create(Todo todo) throws IllegalArgumentException {
        String service = getMethodName();
        validateIsExistUser(todo.getUserId(), service);
        validateContent(todo, service);
        validateCategory(todo.getCategoryId(), service);
        validatePriority(todo.getPriorityId(), service);
        todoRepository.insert(todo);
    }

    public void update(Todo todo) throws IllegalArgumentException {
        String service = getMethodName();
        validateIsExistUser(todo.getUserId(), service);
        validateContent(todo, service);
        validateCategory(todo.getCategoryId(), service);
        validatePriority(todo.getPriorityId(), service);
        todoRepository.update(todo);
    }

    public void toggleStatus(int todoId) throws IllegalArgumentException {
        String service = getMethodName();
        validateIsExistTodo(todoId, service);
        if (todoRepository.findById(todoId).get().getStatusId() == StatusEnum.TODO.getId()) {
            todoRepository.updateStatus(todoId, StatusEnum.DONE);
        } else {
            todoRepository.updateStatus(todoId, StatusEnum.TODO);
        }
    }

    public void deactivate(int todoId) {
        if (todoRepository.findById(todoId).isPresent()) {
            todoRepository.updateDeleteFlag(todoId);
        }
    }

    public Todo findByTodoId(int todoId) {
        String service = getMethodName();
        validateIsExistTodo(todoId, service);
        return todoRepository.findById(todoId).get();
    }

    public List<Todo> findByUserId(int userId) {
        String service = getMethodName();
        validateIsExistUser(userId, service);
        return todoRepository.findByUserId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false))
                .collect(Collectors.toList());
    }

    public List<Todo> findByCategoryId(int userId, int categoryId) {
        String service = getMethodName();
        validateIsExistUser(userId, service);
        validateCategory(categoryId, service);
        return todoRepository.findByUserId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false)
                        && todo.getCategoryId() == categoryId)
                .collect(Collectors.toList());
    }
    public List<Todo> findByStatusId(int userId, int statusId) {
        String service = getMethodName();
        validateIsExistUser(userId, service);
        validatePriority(statusId, service);

        todoRepository.findByStatusId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false) && todo.getStatusId() == statusId);

        return todoRepository.findByUserId(userId)
                .stream()
                .filter(todo -> todo.getIsDeleted().equals(false)
                        && todo.getStatusId() == statusId)
                .sorted(new PriorityComparator())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param userId
     * @param service
     * @return true if user(userId) is exist on repository, otherwise false
     * @throws IllegalArgumentException
     */
    private boolean validateIsExistUser(int userId, String service) throws IllegalArgumentException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getIsDeleted()) {
            logMessage(service, "존재하지 않는 회원입니다.", "");
        }
        return true;
    }

    /**
     *
     * @param todoId
     * @param service
     * @return true if todo(todoId) is exist on repository, otherwise false
     * @throws IllegalArgumentException
     */
    private boolean validateIsExistTodo(int todoId, String service) throws IllegalArgumentException {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isEmpty() || todo.get().getIsDeleted()) {
            logMessage(service, "존재하지 않는 항목입니다.", "");
        }
        return true;
    }

    /**
     *
     * @param todo
     * @param service
     * @return true if todo is vaild, otherwise false
     * @throws IllegalArgumentException
     */
    private boolean validateContent(Todo todo, String service) throws IllegalArgumentException {
        if (todo.getContent().isEmpty()) {
            logMessage(service, "빈 문자열입니다.", "");
        }
        return true;
    }

    /**
     *
     * @param categoryId
     * @param service
     * @return true if categoryId is valid, otherwise false
     * @throws IllegalArgumentException
     */
    private boolean validateCategory(int categoryId, String service) throws IllegalArgumentException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            logMessage(service, "존재하지 않는 카테고리 항목입니다.", "");
        }
        return true;
    }

    /**
     *
     * @param priorityId
     * @param service
     * @return true if priorityId is valid, otherwise false
     * @throws IllegalArgumentException
     */
    private boolean validatePriority(int priorityId, String service) throws IllegalArgumentException {
        Optional<Priority> priority = priorityRepository.findById(priorityId);
        if (priority.isEmpty()) {
            logMessage(service, "존재하지 않는 우선순위 항목입니다.", "");
        }
        return true;
    }

    private void logMessage(String service, String message, String data) throws IllegalArgumentException {
        log.info("TodoService.{}: {} {}", service, message, data);
        throw new IllegalArgumentException(message);
    }

    private String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
