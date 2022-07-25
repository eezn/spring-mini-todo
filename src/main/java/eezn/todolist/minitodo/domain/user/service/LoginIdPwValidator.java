package eezn.todolist.minitodo.domain.user.service;

import eezn.todolist.minitodo.domain.user.model.User;
import eezn.todolist.minitodo.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginIdPwValidator implements UserDetailsService {

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {

        User user = userRepository.findByName(insertedId).get();
        System.out.println(user);

        String pw = user.getPassword();
        String roles = "ROLE";

        return org.springframework.security.core.userdetails.User.builder()
                .username(insertedId)
                .password(pw)
                .roles(roles)
                .build();
    }
}
