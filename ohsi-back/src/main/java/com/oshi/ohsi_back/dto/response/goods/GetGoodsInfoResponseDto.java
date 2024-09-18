package com.oshi.ohsi_back.dto.response.goods;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.ImageEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGoodsInfoResponseDto {

    private GoodsResponseDto goodsResponseDto;

    
    @Builder
    public GetGoodsInfoResponseDto(BaseGoodsEntity baseGoodsEntity, List<ImageEntity> images){
        this.goodsResponseDto = new GoodsResponseDto(baseGoodsEntity, images);
        
    }//이미지가 있을경우의 생성자




}
