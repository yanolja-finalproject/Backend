package com.yanolja_final.domain.auth.facade;

import com.yanolja_final.domain.auth.controller.request.LoginRequest;
import com.yanolja_final.domain.auth.dto.TokenDTO;
import com.yanolja_final.domain.auth.service.AuthService;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;
    private final UserService userService;

    public TokenDTO login(LoginRequest request) {
        User user = userService.findByEmail(request.email());
        return authService.login(user, request.password());
    }
}
