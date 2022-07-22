package eezn.todolist.minitodo.domain.todolist.todo.repository;

import eezn.todolist.minitodo.commons.enums.StatusEnum;
import eezn.todolist.minitodo.domain.todolist.todo.data.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    Todo insert(Todo todo);

    void update(Todo todo);

    void updateStatus(int id, StatusEnum status);

    void updateDeleteFlag(int id);

    Optional<Todo> findById(int id);

    List<Todo> findByUserId(int userId);

    List<Todo> findByCategoryId(int categoryId);

    List<Todo> findByPriorityId(int priorityId);

    List<Todo> findByStatusId(int statusId);

    List<Todo> findAll();

    List<Todo> findActive();

    List<Todo> findDeactivated();

    void clear();
}
