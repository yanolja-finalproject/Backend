package com.yanolja_final.domain.theme.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class ThemeNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.THEME_NOT_FOUND;

    public ThemeNotFoundException() {

        super(ERROR_CODE);
    }
}
