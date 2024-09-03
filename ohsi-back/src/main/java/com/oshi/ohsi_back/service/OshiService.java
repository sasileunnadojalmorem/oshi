package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;

public interface OshiService {
    ResponseEntity<? super OshiResponseDto> postoshi(oshiRequestDto dto, String email );
    
    
}
