package com.oshi.ohsi_back.service;


import com.oshi.ohsi_back.dto.request.auth.SignInRequestDto;
import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;
import com.oshi.ohsi_back.dto.response.auth.SignUpResponseDto;
import com.oshi.ohsi_back.dto.response.auth.SigninResponseDto;

import jakarta.servlet.http.HttpServletRequest;
public interface AuthService {
    
    SignUpResponseDto signUp(SignUpRequestDto dto,HttpServletRequest http);
    SigninResponseDto signIn(SignInRequestDto dto);
    
}   
