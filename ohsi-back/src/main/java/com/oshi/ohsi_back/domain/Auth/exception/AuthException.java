package com.oshi.ohsi_back.domain.Auth.exception;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

public class AuthException extends CustomException {
    
    public AuthException(ErrorCode errorCode){
        super(errorCode);
    }
    public AuthException(ErrorCode errorCode,String message){
        super(errorCode, message);
    }
}
