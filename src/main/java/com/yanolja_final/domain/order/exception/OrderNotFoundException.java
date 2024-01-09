package com.yanolja_final.domain.order.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class OrderNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.ORDER_NOT_FOUND;

    public OrderNotFoundException() {

        super(ERROR_CODE);
    }
}
