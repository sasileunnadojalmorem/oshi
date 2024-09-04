package com.oshi.ohsi_back.dto.response.goods;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGoodsInfoResponseDto extends ResponseDto{

    private BaseGoodsEntity baseGoodsEntity;

    private GetGoodsInfoResponseDto(BaseGoodsEntity baseGoodsEntity) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);    
        this.baseGoodsEntity = baseGoodsEntity;
    }


    public static ResponseEntity<? super GetGoodsInfoResponseDto> success(BaseGoodsEntity baseGoodsEntity) {
        GetGoodsInfoResponseDto responseBody = new GetGoodsInfoResponseDto(baseGoodsEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto responseBody = new ResponseDto(Responsecode.NOT_EXISTED_BOARD, Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }


}
