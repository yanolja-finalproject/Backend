package com.yanolja_final.domain.review.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class UnauthorizedReviewAccessException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED_REVIEW_ACCESS;

    public UnauthorizedReviewAccessException() {

        super(ERROR_CODE);
    }
}
