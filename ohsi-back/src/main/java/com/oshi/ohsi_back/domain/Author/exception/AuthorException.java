package com.oshi.ohsi_back.domain.Author.exception;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

public class AuthorException extends CustomException{
    public AuthorException(ErrorCode errorCode){
        super(errorCode);
    }
    public AuthorException(ErrorCode errorCode, String runtimeValue){
        super(errorCode, runtimeValue);
    }
}
