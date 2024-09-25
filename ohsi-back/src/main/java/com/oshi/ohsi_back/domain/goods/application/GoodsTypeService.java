package com.oshi.ohsi_back.domain.goods.application;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.domain.goods.presentation.dto.goodsType.response.GetGoodsTypeResponseDto;

public interface GoodsTypeService {
    ResponseEntity<GetGoodsTypeResponseDto> getGoodstypeList();
}
