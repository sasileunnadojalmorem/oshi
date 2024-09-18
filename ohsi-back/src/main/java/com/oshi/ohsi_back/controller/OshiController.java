package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.SearchOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.service.OshiService;
import com.oshi.ohsi_back.dto.request.oshi.OshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.SearchOhsiRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/oshi")
@RequiredArgsConstructor
public class OshiController {

    private final OshiService oshiService;

    @PostMapping("add")
    public OshiResponseDto postOshi(
        @ModelAttribute OshiRequestDto requestbody,
        @AuthenticationPrincipal String email
    ) {
        return oshiService.postoshi(requestbody, email);
    }

    @GetMapping("")
    public GetOshiResponseDto getOshi(
        @RequestBody @Valid GetOshiRequestDto requestbody
    ) {
        return oshiService.getoshi(requestbody);
    }

    @GetMapping("/search")
    public SearchOshiResponseDto searchOshi(
        @RequestBody @Valid SearchOhsiRequestDto dto
    ) {
        return oshiService.searchOshi(dto);
    }
}