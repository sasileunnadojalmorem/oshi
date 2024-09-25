package com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.core.common.Responsecode;
import com.oshi.ohsi_back.core.common.Responsemessage;
import com.oshi.ohsi_back.core.properties.ResponseDto;
import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddGoodsResponseDto {



    private GoodsResponseDto goodsResponseDto;

    @Builder
    public AddGoodsResponseDto(BaseGoodsEntity baseGoodsEntity){
        this.goodsResponseDto = new GoodsResponseDto(baseGoodsEntity);
    }
}
