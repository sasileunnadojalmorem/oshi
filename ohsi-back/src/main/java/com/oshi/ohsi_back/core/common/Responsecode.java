package com.oshi.ohsi_back.core.common;

public interface Responsecode {

    // HTTP STATUS 200
    String SUCCESS = "SU";

    // HTTP STATUS 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NAME = "DN";
    String DUPLICATE_OSHI = "DO";
    String DUPLICATE_CATEGORY = "DC";
    String DUPLICATE_GOODS = "DG";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    // HTTP STATUS 401
    String SIGN_IN_FAILED = "SF";
    String AUTHORIZATION_FAIL = "AF";
    String SECURITY_INVALID_TOKEN = "SIT"; // 새로운 에러 코드 추가
    String SECURITY_UNAUTHORIZED = "SUZ";  // 새로운 에러 코드 추가

    // HTTP STATUS 403
    String NO_PERMISSION = "NP";
    String SECURITY_ACCESS_DENIED = "SAD";  // 새로운 에러 코드 추가

    // HTTP STATUS 404
    String NOT_FOUND = "NF";
    String RESOURCE_NOT_FOUND = "RNF";      // 새로운 에러 코드 추가

    // HTTP STATUS 409
    String CONFLICT = "COF";                // 새로운 에러 코드 추가

    // HTTP STATUS 500
    String DATABASE_ERROR = "DBE";
    String SERVER_ERROR = "ISE";            // 새로운 에러 코드 추가

    // HTTP STATUS 503
    String SERVICE_UNAVAILABLE = "SUA";     // 새로운 에러 코드 추가
}