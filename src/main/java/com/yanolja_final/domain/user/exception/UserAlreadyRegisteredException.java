package com.yanolja_final.domain.user.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class UserAlreadyRegisteredException extends ApplicationException {

    public UserAlreadyRegisteredException() {
        super(ErrorCode.USER_ALREADY_REGISTERED);
    }
}
