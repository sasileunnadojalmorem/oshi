package com.oshi.ohsi_back.domain.goods.exception;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

public class GoodsException extends CustomException  {

    public GoodsException(ErrorCode errorCode){
        super(errorCode);
    }

    public GoodsException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
    
}
