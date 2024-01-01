package com.yanolja_final.domain.user.service;

import com.yanolja_final.domain.user.dto.request.CreateUserRequest;
import com.yanolja_final.domain.user.dto.response.CreateUserResponse;
import com.yanolja_final.domain.user.entity.Authority;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.exception.PhoneNumberAlreadyRegisteredException;
import com.yanolja_final.domain.user.exception.UserAlreadyRegisteredException;
import com.yanolja_final.domain.user.exception.UserNotFoundException;
import com.yanolja_final.domain.user.repository.UserRepository;
import com.yanolja_final.global.util.ResponseDTO;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    public static final Set<Authority> DEFAULT_AUTHORITIES =
        Collections.singleton(new Authority("ROLE_USER"));
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseDTO<CreateUserResponse> registerNewUser(CreateUserRequest createUserRequest) {
        userRepository.findByEmail(createUserRequest.email()).ifPresent(user -> {
            throw new UserAlreadyRegisteredException();
        });
        userRepository.findByPhoneNumber(createUserRequest.phoneNumber()).ifPresent(user -> {
            throw new PhoneNumberAlreadyRegisteredException();
        });
        String encodedPassword = passwordEncoder.encode(createUserRequest.password());
        User newUser = User.builder()
            .email(createUserRequest.email())
            .username(createUserRequest.username())
            .encryptedPassword(encodedPassword)
            .phoneNumber(createUserRequest.phoneNumber())
            .authorities(DEFAULT_AUTHORITIES)
            .isTermsAgreed(true)
            .build();

        userRepository.save(newUser);
        return ResponseDTO.okWithData(CreateUserResponse.fromEntity(newUser));
    }

    public ResponseDTO<CreateUserResponse> recoverUser(CreateUserRequest createUserRequest) {
        Optional<User> softDeletedUser = userRepository.findSoftDeletedByEmail(
            createUserRequest.email(), LocalDateTime.now().minusYears(1));

        if (softDeletedUser.isPresent()) {
            User user = softDeletedUser.get();
            user.restore();
            String encodedPassword = passwordEncoder.encode(createUserRequest.password());
            user.updateCredentials(
                createUserRequest.username(),
                createUserRequest.phoneNumber(),
                encodedPassword
            );
            userRepository.save(user);
            CreateUserResponse response = CreateUserResponse.fromEntity(user);
            return ResponseDTO.okWithMessageAndData("계정이 복구되었습니다.", response);
        }
        return null;
    }


    public ResponseDTO<CreateUserResponse> registerOrRecoverUser(
        CreateUserRequest createUserRequest) {
        ResponseDTO<CreateUserResponse> recoveryResponse = recoverUser(createUserRequest);
        if (recoveryResponse != null) {
            return recoveryResponse;
        }
        return registerNewUser(createUserRequest);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);
        if (user.isDeleted()) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());
        user.delete(LocalDateTime.now());
        userRepository.save(user);
    }
}
