package com.oshi.ohsi_back.dto.response.goods;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;

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
