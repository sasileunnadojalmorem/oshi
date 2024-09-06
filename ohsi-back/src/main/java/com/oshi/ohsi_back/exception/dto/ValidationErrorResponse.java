package com.oshi.ohsi_back.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationErrorResponse {

    private String field;
    private String message;
    private Object rejectedValue;
}
