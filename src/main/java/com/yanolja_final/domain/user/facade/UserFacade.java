package com.yanolja_final.domain.user.facade;

import com.yanolja_final.domain.user.dto.request.CreateUserRequest;
import com.yanolja_final.domain.user.dto.response.CreateUserResponse;
import com.yanolja_final.domain.user.service.EmailService;
import com.yanolja_final.domain.user.service.UserService;
import com.yanolja_final.global.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final EmailService emailService;

    public ResponseDTO<CreateUserResponse> signUp(CreateUserRequest createUserRequest) {
        ResponseDTO<CreateUserResponse> signUpResponse = userService.registerOrRecoverUser(createUserRequest);
        return signUpResponse;
    }

    public void sendVerificationEmail(String email) throws Exception {
        emailService.sendVerificationEmail(email);
    }

    public void verifyEmailCode(String email, String code) {
        emailService.verifyEmailCode(email, code);
    }

    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
}
