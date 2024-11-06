package com.oshi.ohsi_back.domain.Auth.application;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignInRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignUpRequestDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.domain.user.execption.UserException;
import com.oshi.ohsi_back.domain.user.infrastructure.UserRepository;
import com.oshi.ohsi_back.domain.user.jwt.service.JwtService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    public void register(SignUpRequestDto signUpRequestDto, HttpServletResponse response) {
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UserException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        // User 엔터티 생성 및 저장
        UserEntity newUser = UserEntity.builder()
            .email(signUpRequestDto.getEmail())
            .username(signUpRequestDto.getUsername())
            .password(encodedPassword)  // 암호화된 비밀번호 저장
            .build();

        userRepository.save(newUser);

        // JWT 토큰 발급 및 응답 헤더에 추가
        addTokensToResponse(newUser.getEmail(), response);
    }

    /**
     * 로그인
     */
    public void login(SignInRequestDto signInRequestDto, HttpServletResponse response) {
        // 이메일로 사용자 조회
        UserEntity user = userRepository.findByEmail(signInRequestDto.getEmail())
            .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTED_USER));

        // 비밀번호 검증
        boolean isPasswordMatch = passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            throw new UserException(ErrorCode.SIGN_IN_FAILED);
        }

        // JWT 토큰 발급 및 응답 헤더에 추가
        addTokensToResponse(user.getEmail(), response);
    }

    /**
     * 토큰 재발급
     */
    public void reissueTokens(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtService.extractRefreshToken(request)
            .orElseThrow(() -> new UserException(ErrorCode.REFRESH_TOKEN_REQUIRED));

        jwtService.isTokenValid(refreshToken);

        // 토큰 재발급 및 응답 헤더에 추가
        jwtService.reissueAndSendTokens(request, response);
    }

    /**
     * 로그아웃
     */
    public void logout(Optional<String> accessToken, Optional<String> refreshToken) {
        String access = accessToken
            .orElseThrow(() -> new UserException(ErrorCode.SECURITY_INVALID_TOKEN));
        String refresh = refreshToken
            .orElseThrow(() -> new UserException(ErrorCode.REFRESH_TOKEN_REQUIRED));

        String email = jwtService.extractEmail(access)
            .orElseThrow(() -> new UserException(ErrorCode.EMAIL_NOT_EXTRACTED));

        jwtService.isTokenValid(refresh);
        jwtService.isTokenValid(access);

        jwtService.deleteSessionCache(refresh);
        jwtService.invalidAccessToken(access);
    }

    /**
     * 회원 탈퇴
     */
    public void withdraw(int id, Optional<String> accessToken, Optional<String> refreshToken) {
        UserEntity user = findUserById(id);
        userRepository.delete(user);

        String access = accessToken
            .orElseThrow(() -> new UserException(ErrorCode.SECURITY_INVALID_TOKEN));
        String refresh = refreshToken
            .orElseThrow(() -> new UserException(ErrorCode.REFRESH_TOKEN_REQUIRED));

        jwtService.isTokenValid(refresh);
        jwtService.isTokenValid(access);
        jwtService.deleteSessionCache(refresh);
        jwtService.invalidAccessToken(access);
    }

    /**
     * 사용자 정보 조회
     */
    public UserEntity findUserById(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserException(ErrorCode.NOT_EXISTED_USER));
    }

    /**
     * 이메일을 통해 JWT 토큰을 생성하고 응답 헤더에 추가하는 메서드
     */
    private void addTokensToResponse(String email, HttpServletResponse response) {
        String accessToken = jwtService.createAccessToken(email);
        String refreshToken = jwtService.createRefreshToken();

        jwtService.updateSessionCache(refreshToken, email);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh-Token", refreshToken);
    }
}