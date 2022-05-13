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

    public int countUser() {
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
        String inputName = user.getUsername();
        if (inputName.length() == 0) {
            throw new IllegalStateException("사용자 이름이 필요합니다.");
        }
        userRepository.findByName(inputName.toLowerCase()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        });
        userRepository.findByName(inputName.toUpperCase()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        });
    }

    private void validateDuplicateEmail(User user) throws IllegalStateException {
        String inputEmail = user.getEmail();
        if (inputEmail.length() == 0) {
            throw new IllegalStateException("사용자 이메일이 필요합니다.");
        }
        userRepository.findByEmail(inputEmail.toLowerCase()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        });
        userRepository.findByEmail(inputEmail.toUpperCase()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        });
    }
}
