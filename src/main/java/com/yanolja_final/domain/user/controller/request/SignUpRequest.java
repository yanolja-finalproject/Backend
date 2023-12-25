package com.yanolja_final.domain.user.controller.request;

import com.yanolja_final.domain.user.entity.Authority;
import com.yanolja_final.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record SignUpRequest(
    @Email(message = "email의 형식이 잘못됐습니다.")
    @NotNull(message = "email은 필수값입니다.")
    String email,

    @Size(min = 6, message = "password는 6자 이상이어야 합니다.")
    @NotNull(message = "password는 필수값입니다.")
    String password
) {

    public User toEntity(String encryptedPassword, Set<Authority> authorities) {
        return User.builder()
            .email(email)
            .encryptedPassword(encryptedPassword)
            .authorities(authorities)
            .build();
    }
}
