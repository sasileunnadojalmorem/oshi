package com.oshi.ohsi_back.dto.response.goodsType;

import java.util.List;

import com.oshi.ohsi_back.dto.response.goods.GoodsResponseDto;

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
