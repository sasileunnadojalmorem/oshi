package com.oshi.ohsi_back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter

public class SignUpResponseDto {

    private String token;
    private int  expirationTime;

    @Builder
    public SignUpResponseDto(String token){

        this.token  = token;
        this.expirationTime = 3600; // 1 hour
    }

}
