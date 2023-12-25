package com.yanolja_final.domain.user.controller.response;

import com.yanolja_final.domain.user.entity.User;

public record SignUpResponse(
    Long id,
    String email
) {

    public static SignUpResponse from(User user) {
        return new SignUpResponse(
            user.getId(),
            user.getEmail()
        );
    }
}
