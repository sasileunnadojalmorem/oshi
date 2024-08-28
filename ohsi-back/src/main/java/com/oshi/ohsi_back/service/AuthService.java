package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;
import com.oshi.ohsi_back.dto.response.auth.SignUpResponseDto;
public interface AuthService {
    
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto );


}   
