package com.yanolja_final.domain.wish.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class WishNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.WISH_NOT_FOUND;

    public WishNotFoundException() {

        super(ERROR_CODE);
    }
}
