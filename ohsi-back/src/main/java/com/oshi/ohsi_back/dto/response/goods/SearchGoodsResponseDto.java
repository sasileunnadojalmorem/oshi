package com.oshi.ohsi_back.dto.response.goods;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchGoodsResponseDto extends ResponseDto  {
    

    private BaseGoodsEntity baseGoodsEntity;

    private SearchGoodsResponseDto(BaseGoodsEntity baseGoodsEntity){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.baseGoodsEntity = baseGoodsEntity;
    }

    public static ResponseEntity<? super SearchGoodsResponseDto> success(BaseGoodsEntity baseGoodsEntity){
        SearchGoodsResponseDto responseDto = new SearchGoodsResponseDto(baseGoodsEntity);
        return ResponseEntity.status(200).body(responseDto);
    }
}