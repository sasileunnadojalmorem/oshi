package com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.core.common.Responsecode;
import com.oshi.ohsi_back.core.common.Responsemessage;
import com.oshi.ohsi_back.core.properties.ResponseDto;
import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetGoodsResponseDto extends ResponseDto {

    private Page<BaseGoodsEntity> goodsPage;


    private GetGoodsResponseDto(Page<BaseGoodsEntity> goodsPage) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);   
        this.goodsPage = goodsPage;
    }

    public static ResponseEntity<? super GetGoodsResponseDto> success(Page<BaseGoodsEntity> goodsPage) {
       GetGoodsResponseDto responseBody = new GetGoodsResponseDto(goodsPage);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
        
    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto responseBody = new ResponseDto(Responsecode.NOT_EXISTED_BOARD, Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    


}
