package com.yanolja_final.domain.user.facade;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.controller.response.SignUpResponse;
import com.yanolja_final.domain.user.service.EmailService;
import com.yanolja_final.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final EmailService emailService;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    public void sendVerificationEmail(String email) throws Exception {
        emailService.sendVerificationEmail(email);
    }

    public void verifyEmailCode(String email, String code) {
        emailService.verifyEmailCode(email, code);
    }
}
