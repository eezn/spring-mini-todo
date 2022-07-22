package eezn.todolist.minitodo.domain.todolist.options.repository;

import eezn.todolist.minitodo.domain.todolist.options.data.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository {

    Status insert(Status status);

    Optional<Status> findById(int id);

    List<Status> findAll();
}
