package eezn.todolist.minitodo.domain.todo.repository;

import eezn.todolist.minitodo.domain.todo.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository {

    Status insert(Status status);

    Optional<Status> findById(int id);

    List<Status> findAll();
}
