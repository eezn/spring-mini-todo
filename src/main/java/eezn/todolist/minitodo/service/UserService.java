package eezn.todolist.minitodo.service;

import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.repository.TodoRepository;
import eezn.todolist.minitodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public User join(User user) throws IllegalStateException {
        validateDuplicateUser(user);
        validateDuplicateEmail(user);
        return userRepository.insert(user);
    }

    public void update(User user) throws IllegalStateException {
        validateUser(user.getId());
        userRepository.update(user);
    }

    public void deactivate(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            List<Todo> todoList = todoRepository.findByUserId(user.getId());
            todoList.forEach(todo -> todoRepository.updateDeleteFlag(todo.getId()));
            userRepository.updateDeleteFlag(user.getId());
        }
    }

    public User findUser(Integer UserId) throws IllegalStateException {
        validateUser(UserId);
        return userRepository.findById(UserId).get();
    }

    public int totalUser() {
        return userRepository.countUser();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void validateUser(Integer UserId) throws IllegalStateException {
        Optional<User> user = userRepository.findById(UserId);
        if (user.isEmpty() || user.get().getIsDeleted()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    private void validateDuplicateUser(User user) throws IllegalStateException {
        if (user.getUsername().length() == 0) {
            throw new IllegalStateException("빈 문자열이 입력될 수 없습니다.");
        }
        userRepository.findByName(user.getUsername()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        });
    }

    private void validateDuplicateEmail(User user) throws IllegalStateException {
        userRepository.findByEmail(user.getEmail()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        });
    }
}
