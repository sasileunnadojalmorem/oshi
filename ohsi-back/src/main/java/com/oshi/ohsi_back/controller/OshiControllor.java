package com.oshi.ohsi_back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.service.OshiService;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/oshi")
@RequiredArgsConstructor
public class OshiControllor {


    private final OshiService oshiService;

    @PostMapping("add")
    public ResponseEntity<? super OshiResponseDto> postOshi(
        @RequestBody@Valid oshiRequestDto requestbody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super OshiResponseDto> response = oshiService.postoshi(requestbody, email);
        return response;

    }
    
}
