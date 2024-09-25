package com.oshi.ohsi_back.exception.exceptionclass;

import com.oshi.ohsi_back.core.properties.ErrorCode;

public class CustomException extends RuntimeException {
    
    private final ErrorCode errorCode;
    private final String runtimeValue;

    public CustomException(ErrorCode errorCode) {
        this(errorCode, "runtimeValue가 존재 하지 않습니다.");
    }

    public CustomException(ErrorCode errorCode, String runtimeValue) {
        this.errorCode = errorCode;
        this.runtimeValue = runtimeValue;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getRuntimeValue() {
        return runtimeValue;
    }

    @Override
    public String getMessage() {
        return String.format("Error Code: %s, Runtime Value: %s", errorCode, runtimeValue);
    }
}
