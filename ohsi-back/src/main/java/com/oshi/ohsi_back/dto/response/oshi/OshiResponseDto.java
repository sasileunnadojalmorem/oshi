package com.oshi.ohsi_back.dto.response.oshi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.OshiEntity;

import lombok.Getter;

@Getter
public class OshiResponseDto extends ResponseDto {


    public OshiResponseDto(OshiEntity oshiEntity) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);


    }

    public static ResponseEntity<OshiResponseDto> success(OshiEntity oshiEntity){
        OshiResponseDto responsebody = new OshiResponseDto(oshiEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);

    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responsebody = new ResponseDto(Responsecode.NOT_EXISTED_USER,Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);

    }
    public static ResponseEntity<ResponseDto> duplicationName() {
        ResponseDto result = new ResponseDto(Responsecode.DUPLICATE_NAME,Responsemessage.DUPLICATE_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
