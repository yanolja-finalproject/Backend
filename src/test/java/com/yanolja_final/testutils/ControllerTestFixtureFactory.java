package com.yanolja_final.testutils;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.repository.UserRepository;
import com.yanolja_final.domain.user.service.UserService;
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
        SignUpRequest request = makeSignUpRequest();

        Long userId = userService.signUp(request).id();
        User user = userRepository.findById(userId).orElseThrow();

        return new UserFixture(request.email(), request.password(), user);
    }

    private SignUpRequest makeSignUpRequest() {
        signUpIdx++;
        String email = "test" + signUpIdx + "@test.com";
        String password = "password" + signUpIdx;

        return new SignUpRequest(
            email,
            password
        );
    }
}
