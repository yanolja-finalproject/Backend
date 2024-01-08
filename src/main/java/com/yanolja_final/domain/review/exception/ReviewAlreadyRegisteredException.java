package com.yanolja_final.domain.review.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class ReviewAlreadyRegisteredException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.REVIEW_ALREADY_REGISTERED;

    public ReviewAlreadyRegisteredException() {

        super(ERROR_CODE);
    }
}
