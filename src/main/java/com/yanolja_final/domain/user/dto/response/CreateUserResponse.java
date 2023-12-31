package com.yanolja_final.domain.user.dto.response;

import com.yanolja_final.domain.user.entity.User;

public record CreateUserResponse(
    String email,
    String username
) {

    public static CreateUserResponse fromEntity(User user) {
        return new CreateUserResponse(
            user.getEmail(),
            user.getUsername()
        );
    }
}
