package com.yanolja_final.domain.user.dto.request;

import com.yanolja_final.domain.user.entity.Authority;
import com.yanolja_final.domain.user.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record CreateUserRequest(

    @NotNull(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    String email,

    @NotNull(message = "사용자 이름을 입력해 주세요.")
    String username,

    @NotNull(message = "비밀 번호를 입력해 주세요.")
    @Size(min = 6, message = "비밀 번호는 6자 이상이어야 합니다.")
    String password,


    @NotNull(message = "이용약관 동의 여부를 체크해 주세요.")
    Boolean isTermsAgreed
) {

    public User toEntity(String encryptedPassword, Set<Authority> authorities) {
        return User.builder()
            .email(email)
            .username(username)
            .encryptedPassword(encryptedPassword)
            .authorities(authorities)
            .isTermsAgreed(isTermsAgreed)
            .build();
    }
}
