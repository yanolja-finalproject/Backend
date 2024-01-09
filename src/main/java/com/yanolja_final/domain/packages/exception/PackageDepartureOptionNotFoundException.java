package com.yanolja_final.domain.packages.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class PackageDepartureOptionNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.PACKAGE_DEPARTURE_OPTION_NOT_FOUND;

    public PackageDepartureOptionNotFoundException() {

        super(ERROR_CODE);
    }
}