package com.yanolja_final.domain.user.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.yanolja_final.domain.user.dto.request.CreateUserRequest;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.exception.UserAlreadyRegisteredException;
import com.yanolja_final.domain.user.exception.UserNotFoundException;
import com.yanolja_final.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
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
        CreateUserRequest request = new CreateUserRequest("a@a.com", "username","test","010-0000-0000",true);

        when(userRepository.findByEmail(anyString()))
            .thenReturn(Optional.empty());
        when(userRepository.save(any(User.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // when, then
        assertThatNoException()
            .isThrownBy(() -> userService.registerOrRecoverUser(request));
    }

    @Test
    @DisplayName("이미 등록된 이메일로 가입하려하면 예외가 발생한다")
    void signUp_fail_for_duplicated_email() {
        // given
        CreateUserRequest request = new CreateUserRequest("a@a.com", "username","test","010-0000-0000",true);

        when(userRepository.findByEmail(anyString()))
            .thenReturn(Optional.of(new User()));

        // when, then
        assertThatThrownBy(() -> userService.registerOrRecoverUser(request))
            .isInstanceOf(UserAlreadyRegisteredException.class);
    }

    @Test
    @DisplayName("이메일로 User 찾기에 성공한다")
    void findByEmail_sucess() {
        // given
        String email = "a@a.com";
        when(userRepository.findByEmail(email))
            .thenReturn(Optional.of(User.builder().email(email).build()));

        // when, then
        assertThatNoException()
            .isThrownBy(() -> userService.findByEmail(email));
    }

    @Test
    @DisplayName("가입되지 않은 이메일이라면 이메일로 User 찾기에 실패한다")
    void findByEmail_fail_for_not_found() {
        // given
        String email = "a@a.com";
        when(userRepository.findByEmail(email))
            .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> userService.findByEmail(email))
            .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("이미 삭제된 사용자를 복구한다")
    void recoverUser_success() {
        // given
        CreateUserRequest request = new CreateUserRequest("a@a.com", "username", "test", "010-0000-0000",true);
        User deletedUser = User.builder().email("a@a.com").build();
        deletedUser.delete(LocalDateTime.now().minusDays(1)); // 가정: 1일 전 삭제된 사용자

        when(userRepository.findSoftDeletedByEmail(anyString(), any(LocalDateTime.class)))
            .thenReturn(Optional.of(deletedUser));
        when(userRepository.save(any(User.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // when, then
        assertThatNoException()
            .isThrownBy(() -> userService.registerOrRecoverUser(request));
    }

    @Test
    @DisplayName("존재하지 않는 사용자 삭제 시도시 예외 발생")
    void deleteUser_fail_for_not_found() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId))
            .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> userService.deleteUser(userId))
            .isInstanceOf(UserNotFoundException.class);
    }
}
