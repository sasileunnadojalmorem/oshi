package com.oshi.ohsi_back.domain.Auth.presentation.dto.response;


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
