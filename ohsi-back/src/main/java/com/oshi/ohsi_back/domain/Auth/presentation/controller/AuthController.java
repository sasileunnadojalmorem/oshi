package com.oshi.ohsi_back.domain.Auth.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.oshi.ohsi_back.domain.Auth.application.AuthService;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignInRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignUpRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SignUpResponseDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SigninResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입
     */
    @PostMapping("/sign-up")
    public SignUpResponseDto signUp(
        @RequestBody @Valid SignUpRequestDto requestBody, HttpServletResponse response
    ) {
        authService.register(requestBody, response);
        return new SignUpResponseDto("회원가입 성공");
    }

    /**
     * 로그인
     */
    @PostMapping("/sign-in")
    public SigninResponseDto signIn(
        @RequestBody @Valid SignInRequestDto requestBody, HttpServletResponse response
    ) {
        authService.login(requestBody, response);
        return new SigninResponseDto("로그인 성공");
    }

    /**
     * 토큰 재발급
     */
    @PostMapping("/reissue-tokens")
    public void reissueTokens(HttpServletRequest request, HttpServletResponse response) {
        authService.reissueTokens(request, response);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public void logout(
        @RequestParam("accessToken") String accessToken,
        @RequestParam("refreshToken") String refreshToken
    ) {
        authService.logout(Optional.of(accessToken), Optional.of(refreshToken));
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/withdraw")
    public void withdraw(
        @RequestParam("userId") int userId,
        @RequestParam("accessToken") String accessToken,
        @RequestParam("refreshToken") String refreshToken
    ) {
        authService.withdraw(userId, Optional.of(accessToken), Optional.of(refreshToken));
    }
}