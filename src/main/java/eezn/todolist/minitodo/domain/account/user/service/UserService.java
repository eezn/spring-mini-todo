package eezn.todolist.minitodo.domain.account.user.service;

import eezn.todolist.minitodo.domain.account.user.data.User;
import eezn.todolist.minitodo.domain.account.user.repository.UserRepository;
import eezn.todolist.minitodo.domain.todolist.todo.data.Todo;
import eezn.todolist.minitodo.domain.todolist.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public User join(User user) throws IllegalArgumentException {
        validateIsDuplicateUser(user, "join");
        validateIsDuplicateEmail(user, "join");
        return userRepository.insert(user);
    }

    public boolean update(User user) throws IllegalArgumentException {
        int userId = user.getId();
        validateIsExistUser(userId, "update");
        userRepository.update(user);
        return true;
    }

    public User findByUserId(Integer userId) throws IllegalArgumentException {
        validateIsExistUser(userId, "findByUserId");
        return userRepository.findById(userId).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int countUser() {
        return userRepository.countUser();
    }

    public void deactivate(User user) throws IllegalArgumentException {
        int userId = user.getId();
        if (validateIsExistUser(userId, "deactivate")) {
            List<Todo> todoList = todoRepository.findByUserId(userId);

            // todo: 쿼리 개선
            todoList.forEach(todo -> todoRepository.updateDeleteFlag(todo.getId()));
            userRepository.updateDeleteFlag(user.getId());
        }
    }

    private boolean validateIsExistUser(Integer userId, String service) throws IllegalArgumentException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getIsDeleted()) {
            logMessage(service, "존재하지 않는 회원입니다.", "");
        }
        return true;
    }

    private void validateIsDuplicateUser(User user, String service) throws IllegalArgumentException {
        String username = user.getUsername();
        if (username.length() == 0) {
            logMessage(service, "아이디를 입력해주세요.", "");
        }
        userRepository.findByName(username.toLowerCase()).ifPresent(m ->
                logMessage(service, "이미 사용중인 아이디입니다.", username));
        userRepository.findByName(username.toUpperCase()).ifPresent(m ->
                logMessage(service, "이미 사용중인 아이디입니다.", username));
    }

    private void validateIsDuplicateEmail(User user, String service) throws IllegalArgumentException {
        String email = user.getEmail();
        if (email.length() == 0) {
            logMessage(service, "이메일을 입력해주세요.", "");
        }
        userRepository.findByEmail(email.toLowerCase()).ifPresent(m ->
                logMessage(service, "이미 사용중인 이메일입니다.", email));
        userRepository.findByEmail(email.toUpperCase()).ifPresent(m ->
                logMessage(service, "이미 사용중인 이메일입니다.", email));
    }

    private void logMessage(String service, String message, String data) throws IllegalArgumentException {
        log.info("UserService.{}: {} {}", service, message, data);
        throw new IllegalArgumentException(message);
    }
}
