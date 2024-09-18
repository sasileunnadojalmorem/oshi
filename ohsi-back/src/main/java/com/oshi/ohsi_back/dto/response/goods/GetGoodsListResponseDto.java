package com.oshi.ohsi_back.dto.response.goods;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGoodsListResponseDto {
    private List<GoodsResponseDto> goodsList;
    private int totalPages;
    private int totalCount;

    @Builder
    public GetGoodsListResponseDto(List<GoodsResponseDto> goodsEntities, int totalPages, int totalCount) {
        this.goodsList = goodsEntities;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}
