package com.oshi.ohsi_back.dto.response.goods;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.ImageEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGoodsInfoResponseDto extends ResponseDto{

    private GoodsResponseDto goodsResponseDto;

    private GetGoodsInfoResponseDto(BaseGoodsEntity baseGoodsEntity) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);    
        this.goodsResponseDto = new GoodsResponseDto(baseGoodsEntity);
    }//이미지가 없을 경우 생성자

    private GetGoodsInfoResponseDto(BaseGoodsEntity baseGoodsEntity, List<ImageEntity> images){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);    
        this.goodsResponseDto = new GoodsResponseDto(baseGoodsEntity, images);
        
    }//이미지가 있을경우의 생성자


    public static ResponseEntity<? super GetGoodsInfoResponseDto> success(BaseGoodsEntity baseGoodsEntity) {
        GetGoodsInfoResponseDto responseBody = new GetGoodsInfoResponseDto(baseGoodsEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super GetGoodsInfoResponseDto> success(BaseGoodsEntity baseGoodsEntity,List<ImageEntity> images) {
        GetGoodsInfoResponseDto responseBody = new GetGoodsInfoResponseDto(baseGoodsEntity,images);
        
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto responseBody = new ResponseDto(Responsecode.NOT_EXISTED_BOARD, Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }


}
