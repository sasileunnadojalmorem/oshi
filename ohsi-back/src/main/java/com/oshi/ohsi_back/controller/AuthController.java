package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.auth.SignInRequestDto;
import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;
import com.oshi.ohsi_back.dto.response.auth.SignUpResponseDto;
import com.oshi.ohsi_back.dto.response.auth.SigninResponseDto;
import com.oshi.ohsi_back.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody, HttpServletRequest http
    ) {
        // 유효성 검사에서 실패하면 전역 예외 처리기로 넘어감
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody, http);
        return response;
    }


    @PostMapping("/sign-in")
    public ResponseEntity<? super SigninResponseDto> signIn(
        @RequestBody@Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SigninResponseDto> response  =  authService.signIn(requestBody);
        return response;
    }

}
