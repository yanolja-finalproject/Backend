package com.yanolja_final.domain.order.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class MaximumCapacityReachedException extends ApplicationException {

    public MaximumCapacityReachedException() {
        super(ErrorCode.MAXIMUN_CAPACITY);
    }
}
