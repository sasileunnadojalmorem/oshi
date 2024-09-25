
package com.oshi.ohsi_back.domain.Auth.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.domain.Auth.application.AuthService;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignInRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignUpRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SignUpResponseDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SigninResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    

    @PostMapping("/sign-up")
    public SignUpResponseDto signUp(
        @RequestBody @Valid SignUpRequestDto requestBody, HttpServletRequest http
    ) {
        // 유효성 검사에서 실패하면 전역 예외 처리기로 넘어감
        SignUpResponseDto response = authService.signUp(requestBody, http);
        return response;
    }


    @PostMapping("/sign-in")
    public SigninResponseDto signIn(
        @RequestBody@Valid SignInRequestDto requestBody
    ){
        SigninResponseDto response  =  authService.signIn(requestBody);
        return response;
    }

}
