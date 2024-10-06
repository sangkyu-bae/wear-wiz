package com.werwiz.infra.excption;

import com.wearwiz.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberError implements ErrorCode {

    MEMBER_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 회원가입 형식입니다"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 회원을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String detail;

    MemberError(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }
    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getDetail() {
        return detail;
    }
}
