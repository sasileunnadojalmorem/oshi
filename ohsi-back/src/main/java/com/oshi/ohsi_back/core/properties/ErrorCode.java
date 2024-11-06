package com.oshi.ohsi_back.core.properties;

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
    NOT_EXISTED_SALES(HttpStatus.BAD_REQUEST, "NO", "This Sales Not Existed"),
    REFRESH_TOKEN_REQUIRED(HttpStatus.BAD_REQUEST, "RTR", "Refresh Token is Required"),  // 추가됨
    EMAIL_NOT_EXTRACTED(HttpStatus.BAD_REQUEST, "ENE", "Email Could Not Be Extracted"),  // 추가됨

    // HTTP STATUS 401
    SIGN_IN_FAILED(HttpStatus.UNAUTHORIZED, "SF", "Sign In Failed"),
    AUTHORIZATION_FAIL(HttpStatus.UNAUTHORIZED, "AF", "Authorization Failed"),
    SECURITY_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "SIT", "Invalid Security Token"),
    SECURITY_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "SUZ", "Unauthorized Access"),
    SECURITY_INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "IAT", "Invalid Access Token"),  // 추가됨

    // HTTP STATUS 403
    NO_PERMISSION(HttpStatus.FORBIDDEN, "NP", "Do Not Have Permission"),
    SECURITY_ACCESS_DENIED(HttpStatus.FORBIDDEN, "SAD", "Access Denied"),

    // HTTP STATUS 404
    NOT_FOUND(HttpStatus.NOT_FOUND, "NF", "Not Found"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "RNF", "Requested Resource Not Found"),

    // HTTP STATUS 409
    CONFLICT(HttpStatus.CONFLICT, "COF", "Conflict Occurred"),

    // HTTP STATUS 500
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DBE", "Database Error"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ISE", "Internal Server Error"),

    // HTTP STATUS 503
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "SUA", "Service Unavailable");

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