package com.oshi.ohsi_back.dto.response.oshi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.OshiEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOshiResponseDto extends ResponseDto {

    private OshiEntity oshiEntity = new OshiEntity();
    
    private GetOshiResponseDto(OshiEntity oshiEntity) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.oshiEntity = oshiEntity;
    }

    public static ResponseEntity<? super GetOshiResponseDto> success(OshiEntity oshiEntity) {
        GetOshiResponseDto responsebody = new GetOshiResponseDto(oshiEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }
}
