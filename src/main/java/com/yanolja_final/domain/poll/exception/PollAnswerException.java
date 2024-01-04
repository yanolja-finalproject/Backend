package com.yanolja_final.domain.poll.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class PollAnswerException extends ApplicationException {

    public PollAnswerException() {
        super(ErrorCode.ALREADY_VOTED);
    }
}
