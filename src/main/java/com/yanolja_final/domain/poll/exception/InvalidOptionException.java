package com.yanolja_final.domain.poll.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class InvalidOptionException extends ApplicationException {

    public InvalidOptionException() {
        super(ErrorCode.INVALID_OPTION);
    }
}
