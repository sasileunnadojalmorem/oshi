package com.oshi.ohsi_back.domain.ohsi.application;


import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.GetOshiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.OshiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.SearchOhsiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.GetOshiResponseDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.SearchOshiResponseDto;

public interface OshiService {
    OshiResponseDto postoshi(OshiRequestDto oshiDto, String email);
    GetOshiResponseDto getoshi(GetOshiRequestDto dto);
    SearchOshiResponseDto searchOshi(SearchOhsiRequestDto dto);
}