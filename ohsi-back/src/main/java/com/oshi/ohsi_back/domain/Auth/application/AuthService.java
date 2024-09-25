package com.oshi.ohsi_back.domain.Auth.application;


import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignInRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignUpRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SignUpResponseDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SigninResponseDto;

import jakarta.servlet.http.HttpServletRequest;
public interface AuthService {
    
    SignUpResponseDto signUp(SignUpRequestDto dto,HttpServletRequest http);
    SigninResponseDto signIn(SignInRequestDto dto);
    
}   
