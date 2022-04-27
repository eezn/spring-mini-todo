package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    int update(User user);

//    int updateName(Integer id, String name);
//
//    int updatePassword(Integer id, String password);
//
//    int updateEmail(Integer id, String email);

    int updateDeleteFlag(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    List<User> findActive();

    List<User> findDeactivated();

    void clear();
}
