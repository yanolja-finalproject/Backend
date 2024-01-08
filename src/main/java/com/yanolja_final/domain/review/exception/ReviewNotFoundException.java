package com.yanolja_final.domain.review.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class ReviewNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.REVIEW_NOT_FOUND;

    public ReviewNotFoundException() {

        super(ERROR_CODE);
    }
}
