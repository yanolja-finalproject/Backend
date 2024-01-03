package com.yanolja_final.domain.user.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
