package com.oshi.ohsi_back.domain.ohsi.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.oshi.ohsi_back.domain.ohsi.application.OshiService;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.GetOshiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.OshiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.SearchOhsiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.GetOshiResponseDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.SearchOshiResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/oshi")
@RequiredArgsConstructor
public class OshiController {

    private final OshiService oshiService;

    // POST 요청으로 Oshi 추가
    @PostMapping("add")
    public OshiResponseDto postOshi(
        @ModelAttribute OshiRequestDto requestbody,
        @AuthenticationPrincipal String email
    ) {
        return oshiService.postoshi(requestbody, email);
    }

    // GET 요청으로 Oshi 정보 조회
    @GetMapping("")
    public GetOshiResponseDto getOshi(
        @ModelAttribute @Valid GetOshiRequestDto requestbody
    ) {
        return oshiService.getoshi(requestbody);
    }

    // GET 요청으로 Oshi 검색 (파라미터로 전달)
    @GetMapping("/search")
    public SearchOshiResponseDto searchOshi(
        @Valid @ModelAttribute SearchOhsiRequestDto dto  // 쿼리 파라미터를 DTO로 매핑
    ) {
        return oshiService.searchOshi(dto);
    }
}