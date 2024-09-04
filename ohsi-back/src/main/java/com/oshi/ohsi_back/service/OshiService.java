package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.goods.SearchOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.SearchOshiResponseDto;

public interface OshiService {
    ResponseEntity<? super OshiResponseDto> postoshi(oshiRequestDto dto, String email );
    ResponseEntity<? super GetOshiResponseDto> getoshi(GetOshiRequestDto dto);
    ResponseEntity<? super SearchOshiResponseDto> searchOshi(SearchOshiRequestDto dto);
}
