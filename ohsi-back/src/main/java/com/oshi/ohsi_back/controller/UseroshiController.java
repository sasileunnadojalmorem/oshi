package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.oshi.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.dto.response.oshi.AddUserOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.GetUserOshiResponseDto;
import com.oshi.ohsi_back.service.UserOshiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user/oshi")
@RequiredArgsConstructor
public class UseroshiController {

    private final UserOshiService userOshiService;
    @PostMapping("/")
    ResponseEntity<? super AddUserOshiResponseDto> useroshi(
        @RequestBody@Valid AddUserOshiRequsetDto dto, @AuthenticationPrincipal String email
    )
    {
        ResponseEntity<? super AddUserOshiResponseDto> response = userOshiService.findstate(dto, email);
        return response;
    }
    
    @GetMapping("get")
    ResponseEntity<? super GetUserOshiResponseDto> getUserOshi(
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super GetUserOshiResponseDto> response = userOshiService.GetUserOshi(email);
        return response;

    }
}
