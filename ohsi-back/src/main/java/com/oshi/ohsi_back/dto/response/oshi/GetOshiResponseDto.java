package com.oshi.ohsi_back.dto.response.oshi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;


@Getter

public class GetOshiResponseDto extends ResponseDto {

    private OshiResponseDto oshiResponseDto;
    
    private GetOshiResponseDto(OshiResponseDto dto) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.oshiResponseDto = dto;
    }

    public static ResponseEntity<? super GetOshiResponseDto> success(OshiResponseDto dto) {
        GetOshiResponseDto responsebody = new GetOshiResponseDto(dto);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }
}
