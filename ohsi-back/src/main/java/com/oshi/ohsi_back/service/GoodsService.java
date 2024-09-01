package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;

public interface GoodsService {

    ResponseEntity<? super AddGoodsResponseDto> AddOshi(AddGoodsRequestDto dto,String email);
    
    
}
