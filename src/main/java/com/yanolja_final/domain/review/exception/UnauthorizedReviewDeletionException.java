package com.yanolja_final.domain.review.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class UnauthorizedReviewDeletionException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED_REVIEW_DELETION;

    public UnauthorizedReviewDeletionException() {

        super(ERROR_CODE);
    }
}
