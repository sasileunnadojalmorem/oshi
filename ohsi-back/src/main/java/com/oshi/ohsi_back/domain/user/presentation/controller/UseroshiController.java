

package com.oshi.ohsi_back.domain.user.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.domain.user.application.useroshi.UserOshiService;
import com.oshi.ohsi_back.domain.user.presentation.dto.request.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.AddUserOshiResponseDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetUserOshiResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user/oshi")
@RequiredArgsConstructor
public class UseroshiController {

    private final UserOshiService userOshiService;

    @PostMapping("/")
    public AddUserOshiResponseDto useroshi(
        @RequestBody @Valid AddUserOshiRequsetDto dto, 
        @AuthenticationPrincipal String email
    ) {
        return userOshiService.findstate(dto, email);
    }
    
    @GetMapping("get")
    public GetUserOshiResponseDto getUserOshiList(
        @AuthenticationPrincipal String email
    ) {
        return userOshiService.GetUserOshi(email);
    }
}