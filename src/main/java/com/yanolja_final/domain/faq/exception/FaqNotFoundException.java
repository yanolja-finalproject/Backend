package com.yanolja_final.domain.faq.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class FaqNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.FAQ_NOT_FOUND;

    public FaqNotFoundException() { super(ERROR_CODE); }



}
