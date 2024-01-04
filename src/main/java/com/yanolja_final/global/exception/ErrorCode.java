package com.yanolja_final.global.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {


    // EMAIL
    EMAIL_SENDING_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송에 실패했습니다."),
    EMAIL_TEMPLATE_LOAD_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 템플릿 로드에 실패했습니다."),
    EMAIL_VERIFY_FAILURE(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않습니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    USER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    PHONE_NUMBER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 등록된 핸드폰 번호입니다."),

    // AUTH
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),

    // POLL
    POLL_NOT_FOUND(HttpStatus.BAD_REQUEST, "등록된 찬/반 토론 이벤트가 없습니다."),
    ALREADY_VOTED(HttpStatus.FORBIDDEN, "이미 투표에 참여 하셨습니다."),

    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
