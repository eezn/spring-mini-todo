package eezn.todolist.minitodo.domain.account.user.repository;

import eezn.todolist.minitodo.domain.account.user.data.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    void update(User user);

    void updateDeleteFlag(int id);

    Optional<User> findById(int id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    List<User> findActive();

    List<User> findDeactivated();

    int countUser();

    void clear();
}
