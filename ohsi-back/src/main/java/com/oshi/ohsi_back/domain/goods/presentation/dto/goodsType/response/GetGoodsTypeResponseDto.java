package com.oshi.ohsi_back.domain.goods.presentation.dto.goodsType.response;

import java.util.List;

import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.GoodsResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGoodsTypeResponseDto {
    private List<GoodsTypeDto> entities;

    @Builder
    public GetGoodsTypeResponseDto(List<GoodsTypeDto> dto){
        this.entities =  dto;
    }
}
