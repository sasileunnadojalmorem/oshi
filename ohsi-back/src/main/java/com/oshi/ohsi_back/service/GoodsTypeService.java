package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.response.goodsType.GetGoodsTypeResponseDto;

public interface GoodsTypeService {
    ResponseEntity<GetGoodsTypeResponseDto> getGoodstypeList();
}
