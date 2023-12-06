package com.yanolja_final.global.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;


    protected ApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    protected ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return errorCode.getMessage();
        }

        return super.getMessage();
    }
}
