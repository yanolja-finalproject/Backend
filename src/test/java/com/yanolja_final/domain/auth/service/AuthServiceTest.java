package com.yanolja_final.domain.auth.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.yanolja_final.domain.auth.exception.InvalidPasswordException;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.global.config.security.jwt.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("로그인에 성공한다")
    void login_success() {
        // given
        User user = User.builder().encryptedPassword("encryptedPassword").build();
        when(passwordEncoder.matches("plainPassword", "encryptedPassword"))
            .thenReturn(true);

        // when, then
        assertThatNoException()
            .isThrownBy(() -> authService.login(user, "plainPassword"));
    }

    @Test
    @DisplayName("비밀번호가 올바르지 않으면 로그인에 실패한다")
    void login_fail_for_invalid_password() {
        // given
        User user = User.builder().encryptedPassword("encryptedPassword").build();
        when(passwordEncoder.matches("plainPassword", "encryptedPassword"))
            .thenReturn(false);

        // when, then
        assertThatThrownBy(() -> authService.login(user, "plainPassword"))
            .isInstanceOf(InvalidPasswordException.class);
    }
}
