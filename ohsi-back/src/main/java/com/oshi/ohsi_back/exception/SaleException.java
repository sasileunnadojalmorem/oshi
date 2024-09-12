package com.oshi.ohsi_back.exception;

import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;

public class SaleException extends CustomException {

    public SaleException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public SaleException(ErrorCode errorCode, String runtimeValue) {
        super(errorCode, runtimeValue);
    }
    
}
