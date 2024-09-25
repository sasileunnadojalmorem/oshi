package com.oshi.ohsi_back.domain.ohsi.exception;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

public class OshiException extends CustomException {
    public OshiException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public OshiException(ErrorCode errorCode, String runtimeValue) {
        super(errorCode, runtimeValue);
    }
}
