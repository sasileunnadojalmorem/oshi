package com.oshi.ohsi_back.exception.exceptionclass;

import com.oshi.ohsi_back.properties.ErrorCode;

public class CustomException extends  RuntimeException {
    
    private final ErrorCode errorCode;
    private final String runtimevalue;

    public CustomException(ErrorCode errorCode) {
        this(errorCode, "runtimeValue가 존재 하지 않습니다.");
    }

    public CustomException(ErrorCode errorCode, String runtimeValue) {
        this.errorCode = errorCode;
        this.runtimevalue = runtimeValue;
    }

}
