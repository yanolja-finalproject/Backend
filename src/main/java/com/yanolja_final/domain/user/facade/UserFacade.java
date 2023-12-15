package com.yanolja_final.domain.user.facade;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.controller.response.SignUpResponse;
import com.yanolja_final.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }
}
