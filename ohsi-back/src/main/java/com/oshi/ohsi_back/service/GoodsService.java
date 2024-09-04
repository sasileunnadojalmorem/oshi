package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsResponseDto;

public interface GoodsService {

    ResponseEntity<? super AddGoodsResponseDto> AddGoods(AddGoodsRequestDto dto,String email);
    ResponseEntity<? super GetGoodsResponseDto> GetGoods(GetGoodsRequestDto dto);
    ResponseEntity<? super GetGoodsInfoResponseDto> GetGoodsInfo(GetGoodsInfoRequsetDto dto);
}
