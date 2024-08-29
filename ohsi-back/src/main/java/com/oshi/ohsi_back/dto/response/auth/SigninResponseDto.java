package com.oshi.ohsi_back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;
@Getter
public class SigninResponseDto extends ResponseDto {

    private String token;
    private int  expirationTime;

    private SigninResponseDto(String token){

        super(Responsecode.SUCCESSS,Responsemessage.SUCCESSS);
        this.token  = token;
        this.expirationTime = 3600; // 1 hour
    }

    public static ResponseEntity<SigninResponseDto> success(String token) {
        SigninResponseDto result = new SigninResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> SIGN_IN_FAILED() {
        ResponseDto result = new ResponseDto(Responsecode.SIGN_IN_FAILED,Responsemessage.SIGN_IN_FAILED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
