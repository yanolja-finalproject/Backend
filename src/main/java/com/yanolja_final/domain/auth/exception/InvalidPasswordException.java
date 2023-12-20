package com.yanolja_final.domain.auth.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class InvalidPasswordException extends ApplicationException {

    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
