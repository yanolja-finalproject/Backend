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

    // PACKAGE
    PACKAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 패키지입니다."),

    // ORDER
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다."),

    // WISH
    WISH_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 찜ID 입니다."),

    // REVIEW
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),
    UNAUTHORIZED_REVIEW_DELETION(HttpStatus.FORBIDDEN, "리뷰 삭제 권한이 없습니다."),

    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
