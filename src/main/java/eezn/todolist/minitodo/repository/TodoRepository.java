package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.StatusEnum;
import eezn.todolist.minitodo.domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    Todo insert(Todo todo);

    void update(Todo todo);

    void updateStatus(Integer id, StatusEnum status);

    void updateDeleteFlag(Todo todo);

    Optional<Todo> findById(Integer id);

    List<Todo> findByUserId(Integer userId);

    List<Todo> findByCategoryId(Integer categoryId);

    List<Todo> findByPriorityId(Integer priorityId);

    List<Todo> findByStatusId(Integer statusId);

    List<Todo> findAll();

    List<Todo> findActive();

    List<Todo> findDeactivated();

    void clear();
}
