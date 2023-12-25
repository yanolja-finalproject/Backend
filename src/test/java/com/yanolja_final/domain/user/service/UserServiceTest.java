package com.yanolja_final.domain.user.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.exception.UserAlreadyRegisteredException;
import com.yanolja_final.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입에 성공한다")
    void signUp_success() {
        // given
        SignUpRequest request = new SignUpRequest("a@a.com", "password");

        when(userRepository.existsByEmail(anyString()))
            .thenReturn(false);
        when(userRepository.save(any(User.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // when, then
        assertThatNoException()
            .isThrownBy(() -> userService.signUp(request));
    }

    @Test
    @DisplayName("이미 등록된 이메일이라면 예외가 발생한다")
    void signUp_fail_for_duplicated_email() {
        // given
        SignUpRequest request = new SignUpRequest("a@a.com", "password");

        when(userRepository.existsByEmail(anyString()))
            .thenReturn(true);

        // when, then
        assertThatThrownBy(() -> userService.signUp(request))
            .isInstanceOf(UserAlreadyRegisteredException.class);
    }
}
