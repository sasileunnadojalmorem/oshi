package com.oshi.ohsi_back.domain.category.exception;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

public class CategoryException extends CustomException{
    
    public CategoryException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public CategoryException(ErrorCode errorCode, String runtimevalue){
        super(errorCode, runtimevalue);
    }
}
