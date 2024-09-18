package com.oshi.ohsi_back.service;


import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.SearchOhsiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.OshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.SearchOshiResponseDto;

public interface OshiService {
    OshiResponseDto postoshi(OshiRequestDto oshiDto, String email);
    GetOshiResponseDto getoshi(GetOshiRequestDto dto);
    SearchOshiResponseDto searchOshi(SearchOhsiRequestDto dto);
}