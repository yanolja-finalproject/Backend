package com.yanolja_final.domain.notice.exception;

import com.yanolja_final.global.exception.ApplicationException;
import com.yanolja_final.global.exception.ErrorCode;

public class NoticeNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.NOTICE_NOT_FOUND;

    public NoticeNotFoundException() { super(ERROR_CODE); }

}
