package eezn.todolist.minitodo.domain.todolist.options.repository;

import eezn.todolist.minitodo.domain.todolist.options.data.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository {

    Priority insert(Priority priority);

    Optional<Priority> findById(Integer id);

    List<Priority> findAll();
}
