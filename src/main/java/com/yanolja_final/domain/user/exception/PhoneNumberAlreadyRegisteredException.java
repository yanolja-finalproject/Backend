package com.yanolja_final.domain.user.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class PhoneNumberAlreadyRegisteredException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.PHONE_NUMBER_ALREADY_REGISTERED;

    public PhoneNumberAlreadyRegisteredException() {
        super(ERROR_CODE);
    }
}
