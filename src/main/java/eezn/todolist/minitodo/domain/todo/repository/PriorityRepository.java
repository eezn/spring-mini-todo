package eezn.todolist.minitodo.domain.todo.repository;

import eezn.todolist.minitodo.domain.todo.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository {

    Priority insert(Priority priority);

    Optional<Priority> findById(int id);

    List<Priority> findAll();
}
