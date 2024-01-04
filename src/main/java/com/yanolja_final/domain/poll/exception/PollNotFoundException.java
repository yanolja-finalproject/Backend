package com.yanolja_final.domain.poll.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class PollNotFoundException extends ApplicationException {

    public PollNotFoundException() {
        super(ErrorCode.POLL_NOT_FOUND);
    }
}
