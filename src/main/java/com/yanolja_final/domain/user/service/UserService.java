package com.yanolja_final.domain.user.service;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.controller.response.SignUpResponse;
import com.yanolja_final.domain.user.entity.Authority;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.exception.UserAlreadyRegisteredException;
import com.yanolja_final.domain.user.exception.UserNotFoundException;
import com.yanolja_final.domain.user.repository.UserRepository;
import java.util.Collections;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    public static final Set<Authority> DEFAULT_AUTHORITIES =
        Collections.singleton(new Authority("ROLE_USER"));
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new UserAlreadyRegisteredException();
        }

        String encryptedPassword = passwordEncoder.encode(signUpRequest.password());
        User user = signUpRequest.toEntity(encryptedPassword, DEFAULT_AUTHORITIES);

        User savedUser = userRepository.save(user);
        return SignUpResponse.from(savedUser);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);
    }
}
