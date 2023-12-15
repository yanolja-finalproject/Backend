package com.yanolja_final.domain.user.service;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.controller.response.SignUpResponse;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.exception.UserAlreadyRegisteredException;
import com.yanolja_final.domain.user.repository.UserRepository;
import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new UserAlreadyRegisteredException();
        }

        String encryptedPassword = passwordEncoder.encode(signUpRequest.password());
        User user = signUpRequest.toEntity(encryptedPassword);

        User savedUser = userRepository.save(user);
        return SignUpResponse.from(savedUser);
    }
}
