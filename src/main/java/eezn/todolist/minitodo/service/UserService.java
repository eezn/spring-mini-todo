package eezn.todolist.minitodo.service;

import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateTodoRepository;
import eezn.todolist.minitodo.repository.jdbctemplate.JdbcTemplateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplateUserRepository userRepository;
    private final JdbcTemplateTodoRepository todoRepository;

    public void join(User user) throws IllegalStateException {
        validateDuplicateUser(user);
        validateDuplicateEmail(user);
        userRepository.insert(user);
    }

    public void update(User user) throws IllegalStateException {
        validateDuplicateUser(user);
        validateDuplicateEmail(user);
        userRepository.update(user);
    }

    private void validateDuplicateUser(User user) throws IllegalStateException {
        userRepository.findByName(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }

    private void validateDuplicateEmail(User user) throws IllegalStateException {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 사용중인 이메일입니다.");
                });
    }

    public void deactivate(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            List<Todo> todoList = todoRepository.findByUserId(user.getId());
            todoList.forEach(todoRepository::updateDeleteFlag);
            userRepository.updateDeleteFlag(user);
        }
    }

    public User findUser(Integer UserId) {
        Optional<User> user = userRepository.findById(UserId);
        if (user.isEmpty() || user.get().getIsDeleted()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return user.get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
