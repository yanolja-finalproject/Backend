package com.yanolja_final.testutils;

import com.yanolja_final.domain.user.dto.request.CreateUserRequest;
import com.yanolja_final.domain.user.dto.response.CreateUserResponse;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.repository.UserRepository;
import com.yanolja_final.domain.user.service.UserService;
import com.yanolja_final.global.util.ResponseDTO;
import com.yanolja_final.testutils.fixture.UserFixture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControllerTestFixtureFactory {

    private final UserService userService;
    private final UserRepository userRepository;

    private int signUpIdx = 0;

    public UserFixture signUp() {
        CreateUserRequest request = makeSignUpRequest();

        ResponseDTO<CreateUserResponse> response = userService.registerOrRecoverUser(request);
        Long userId = userRepository.findByEmail(request.email()).orElseThrow().getId();
        User user = userRepository.findById(userId).orElseThrow();


        return new UserFixture(request.email(), request.password(), user);
    }

    private CreateUserRequest makeSignUpRequest() {
        signUpIdx++;
        String email = "test" + signUpIdx + "@naver.com";
        String username = "username" + signUpIdx;
        String password = "password" + signUpIdx;
        Boolean isTermsAgreed = true;

        return new CreateUserRequest(
            email,
            username,
            password,
            isTermsAgreed
        );
    }
}
