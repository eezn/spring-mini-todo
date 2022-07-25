package eezn.todolist.minitodo.domain.user.service;

import eezn.todolist.minitodo.domain.user.model.User;
import eezn.todolist.minitodo.domain.user.repository.UserRepository;
import eezn.todolist.minitodo.domain.todo.model.Todo;
import eezn.todolist.minitodo.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    private final PasswordEncoder passwordEncoder;

    public User join(User user) throws IllegalArgumentException {
        String service = getMethodName();
        validateIsDuplicateUser(user, service);
        validateIsDuplicateEmail(user, service);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.insert(user);
    }

    public boolean update(User user) throws IllegalArgumentException {
        int userId = user.getId();
        String service = getMethodName();
        validateIsExistUser(userId, service);
        userRepository.update(user);
        return true;
    }

    public User findByUserId(int userId) throws IllegalArgumentException {
        String service = getMethodName();
        validateIsExistUser(userId, service);
        return userRepository.findById(userId).get();
    }

    public User findByUsername(String username) throws IllegalArgumentException {
        return userRepository.findByName(username).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int countUser() {
        return userRepository.countUser();
    }

    public void deactivate(User user) throws IllegalArgumentException {
        int userId = user.getId();
        String service = getMethodName();
        if (validateIsExistUser(userId, service)) {
            List<Todo> todoList = todoRepository.findByUserId(userId);

            // todo: 쿼리 개선
            todoList.forEach(todo -> todoRepository.updateDeleteFlag(todo.getId()));
            userRepository.updateDeleteFlag(user.getId());
        }
    }

    /**
     *
     * @param userId
     * @param service
     * @return true if user(userId) is exist on repository, otherwise false
     * @throws IllegalArgumentException
     */
    private boolean validateIsExistUser(int userId, String service) throws IllegalArgumentException {
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
        // todo: 대소문자 설정 DB쪽에서 변경하기
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
        // todo: 대소문자 설정 DB쪽에서 변경하기
        userRepository.findByEmail(email.toLowerCase()).ifPresent(m ->
                logMessage(service, "이미 사용중인 이메일입니다.", email));
        userRepository.findByEmail(email.toUpperCase()).ifPresent(m ->
                logMessage(service, "이미 사용중인 이메일입니다.", email));
    }

    private void logMessage(String service, String message, String data) throws IllegalArgumentException {
        log.info("UserService.{}: {} {}", service, message, data);
        throw new IllegalArgumentException(message);
    }

    private String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
