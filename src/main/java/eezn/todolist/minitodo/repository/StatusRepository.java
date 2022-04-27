package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository {

    Status insert(Status status);

    Optional<Status> findById(Integer id);

    List<Status> findAll();
}
