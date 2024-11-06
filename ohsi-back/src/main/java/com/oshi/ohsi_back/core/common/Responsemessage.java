package com.oshi.ohsi_back.core.common;

public interface Responsemessage {

    String SUCCESS = "Success";

    // HTTP STATUS 400
    String VALIDATION_FAILED = "Validation Failed";
    String DUPLICATE_EMAIL = "This Email Already Existed";
    String DUPLICATE_NAME= "This Name Already Existed";
    String DUPLICATE_OSHI = "This Oshi Already Existed";
    String DUPLICATE_CATEGORY = "This Category Already Existed";
    String DUPLICATE_GOODS = "This Goods Already Existed";

    String NOT_EXISTED_USER ="This User Not Existed";
    String NOT_EXISTED_BOARD = "This Board Not Existed";

    // HTTP STATUS 401
    String SECURITY_INVALID_TOKEN = "Invalid security token";
    String SIGN_IN_FAILED = "Sign In Failed";
    String AUTHORIZATION_FAIL = "Authorization Failed";

    // HTTP STATUS 403
    String NO_PERMISSION = "Do not have permission";

    // HTTP STATUS 404
    String NOT_FOUND = "Not Found";
    String RESOURCE_NOT_FOUND = "Requested resource not found"; // 새로운 에러 코드 추가

    // HTTP STATUS 409
    String CONFLICT = "Conflict occurred"; // 새로운 에러 코드 추가

    // HTTP STATUS 500
    String DATABASE_ERROR = "Database error";
    String SERVER_ERROR = "Internal server error"; // 새로운 에러 코드 추가

    // HTTP STATUS 503
    String SERVICE_UNAVAILABLE = "Service is currently unavailable"; // 새로운 에러 코드 추가
}