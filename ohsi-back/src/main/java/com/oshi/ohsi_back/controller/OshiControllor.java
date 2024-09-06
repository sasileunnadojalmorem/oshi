package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.SearchOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.service.OshiService;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.SearchOhsiRequestDto;

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
    
    @GetMapping("")
    public ResponseEntity<? super GetOshiResponseDto> getOshi(
        @RequestBody@Valid GetOshiRequestDto requestbody
    ){
        ResponseEntity<? super GetOshiResponseDto> response = oshiService.getoshi(requestbody);
        return response;
    }
    
    @GetMapping("/search")
    public ResponseEntity<? super SearchOshiResponseDto> searchOshi(
        @RequestBody@Valid SearchOhsiRequestDto dto
    ){
        ResponseEntity<? super SearchOshiResponseDto> responseEntity = oshiService.searchOshi(dto);
        return responseEntity;
    }
}