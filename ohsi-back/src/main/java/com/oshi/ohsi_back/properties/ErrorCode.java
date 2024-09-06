package com.oshi.ohsi_back.properties;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // HTTP STATUS 200
    SUCCESS(HttpStatus.OK, "SU", "Success"),
    
    // HTTP STATUS 400
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VF", "Validation Failed"),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "DE", "This Email Already Existed"),
    DUPLICATE_NAME(HttpStatus.BAD_REQUEST, "DN", "This Name Already Existed"),
    DUPLICATE_OSHI(HttpStatus.BAD_REQUEST, "DO", "This Oshi Already Existed"),
    DUPLICATE_CATEGORY(HttpStatus.BAD_REQUEST, "DC", "This Category Already Existed"),
    DUPLICATE_GOODS(HttpStatus.BAD_REQUEST, "DG", "This Goods Already Existed"),
    NOT_EXISTED_USER(HttpStatus.BAD_REQUEST, "NU", "This User Not Existed"),
    NOT_EXISTED_BOARD(HttpStatus.BAD_REQUEST, "NB", "This Board Not Existed"),
    
    // HTTP STATUS 401
    SIGN_IN_FAILED(HttpStatus.UNAUTHORIZED, "SF", "Sign In Failed"),
    AUTHORIZATION_FAIL(HttpStatus.UNAUTHORIZED, "AF", "Authorization Failed"),
    
    // HTTP STATUS 403
    NO_PERMISSION(HttpStatus.FORBIDDEN, "NP", "Do Not Have Permission"),
    
    // HTTP STATUS 404
    NOT_FOUND(HttpStatus.NOT_FOUND, "NF", "Not Found"),
    
    // HTTP STATUS 500
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DBE", "Database Error");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}