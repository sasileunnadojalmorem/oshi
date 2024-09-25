package com.oshi.ohsi_back.domain.Auth.presentation.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.core.common.Responsecode;
import com.oshi.ohsi_back.core.common.Responsemessage;
import com.oshi.ohsi_back.core.properties.ResponseDto;

import lombok.Builder;
import lombok.Getter;
@Getter
public class SigninResponseDto  {

    private String token;
    private int  expirationTime;

    @Builder
    public SigninResponseDto(String token){

        this.token  = token;
        this.expirationTime = 3600; // 1 hour
    }


    
}
