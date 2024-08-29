package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.auth.SignInRequestDto;
import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;
import com.oshi.ohsi_back.dto.response.auth.SignUpResponseDto;
import com.oshi.ohsi_back.dto.response.auth.SigninResponseDto;
public interface AuthService {
    
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto );
    ResponseEntity<? super SigninResponseDto> signIn(SignInRequestDto dto);
    

}   
