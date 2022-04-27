package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository {

    Priority insert(Priority priority);

    Optional<Priority> findById(Integer id);

    List<Priority> findAll();
}
