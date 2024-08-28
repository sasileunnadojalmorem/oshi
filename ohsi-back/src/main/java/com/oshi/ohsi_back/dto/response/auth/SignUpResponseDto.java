package com.oshi.ohsi_back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter

public class SignUpResponseDto extends ResponseDto{

    private SignUpResponseDto(){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
    }
    

    public static ResponseEntity<SignUpResponseDto> success() {
        SignUpResponseDto responseBody = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    

    public static ResponseEntity<ResponseDto> duplicationEmail() {
        ResponseDto result = new ResponseDto(Responsecode.DUPLICATE_EMAIL,Responsemessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicationName() {
        ResponseDto result = new ResponseDto(Responsecode.DUPLICATE_NAME,Responsemessage.DUPLICATE_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        return ResponseDto.databaseError();
    }

}
