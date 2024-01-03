package com.yanolja_final.domain.packages.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class PackageNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.PACKAGE_NOT_FOUND;

    public PackageNotFoundException() {

        super(ERROR_CODE);
    }
}