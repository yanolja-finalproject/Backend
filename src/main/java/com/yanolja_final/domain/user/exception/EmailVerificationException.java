package com.yanolja_final.domain.user.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class EmailVerificationException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_VERIFY_FAILURE;

    public EmailVerificationException() {
        super(ERROR_CODE);
    }
}
