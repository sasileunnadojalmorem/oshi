package com.oshi.ohsi_back.domain.user.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.domain.user.application.user.UserService;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetSigninUserResponseDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public GetSigninUserResponseDto getSignInUser(@AuthenticationPrincipal UserEntity user) {
        return userService.getUserInformation(user);
    }
}