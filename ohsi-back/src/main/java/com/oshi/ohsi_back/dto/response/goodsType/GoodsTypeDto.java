package com.oshi.ohsi_back.dto.response.goodsType;

import com.oshi.ohsi_back.entity.GoodsTypeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoodsTypeDto {

    
    private int typeId;
    private String typeName;

    @Builder
    public GoodsTypeDto(GoodsTypeEntity goodsTypeEntity) {
        this.typeId = goodsTypeEntity.getId();
        this.typeName = goodsTypeEntity.getName();
    }

}
