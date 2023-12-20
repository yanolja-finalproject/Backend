package com.yanolja_final.testutils.fixture;

import com.yanolja_final.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserFixture {

    private final String email;
    private final String password;
    private final User user;
}
