package com.oshi.ohsi_back.service;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsResponseDto;

public interface GoodsService {

    ResponseEntity<? super AddGoodsResponseDto> AddGoods(AddGoodsRequestDto dto,String email);
    ResponseEntity<? super GetGoodsResponseDto> GetGoods(GetGoodsRequestDto dto);
    
}
