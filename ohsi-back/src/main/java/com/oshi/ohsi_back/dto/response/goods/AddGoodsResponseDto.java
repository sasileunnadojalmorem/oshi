package com.oshi.ohsi_back.dto.response.goods;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class AddGoodsResponseDto extends ResponseDto{

    private AddGoodsResponseDto (){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);    
    }

    public static ResponseEntity<AddGoodsResponseDto> success() {
        AddGoodsResponseDto responseBody = new AddGoodsResponseDto();
        return ResponseEntity.status(200).body(responseBody);
    }
    
    
    public static ResponseEntity<ResponseDto> duplicationName() {
        ResponseDto result = new ResponseDto(Responsecode.DUPLICATE_NAME,Responsemessage.DUPLICATE_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
   
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responsebody = new ResponseDto(Responsecode.NOT_EXISTED_USER,Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);

    }
    
    public static ResponseEntity<ResponseDto> databaseError() {
        return ResponseDto.databaseError();
    }
}
