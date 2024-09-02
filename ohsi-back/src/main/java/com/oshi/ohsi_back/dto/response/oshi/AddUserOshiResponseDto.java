package com.oshi.ohsi_back.dto.response.oshi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class AddUserOshiResponseDto extends ResponseDto {

    private int userOshiState;

    // 기본 생성자
    AddUserOshiResponseDto(int userOshiState){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.userOshiState = userOshiState;
    }
    
    // 성공 응답을 반환하는 메서드
    public static ResponseEntity<? super AddUserOshiResponseDto> success(){
        AddUserOshiResponseDto responseEntity = new AddUserOshiResponseDto(1);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }
    
    // 유저가 존재하지 않을 때의 응답을 반환하는 메서드
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responsebody = new ResponseDto(Responsecode.NOT_EXISTED_USER, Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);
    }
    
    // 이미 오시 정보가 존재할 때의 응답을 반환하는 메서드
    public static ResponseEntity<? super AddUserOshiResponseDto> alreadyExists() {
        AddUserOshiResponseDto responseEntity = new AddUserOshiResponseDto(1);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }
    
    // 오시 정보가 존재하지 않을 때의 응답을 반환하는 메서드
    public static ResponseEntity<? super AddUserOshiResponseDto> doesNotExist() {
        AddUserOshiResponseDto responseEntity = new AddUserOshiResponseDto(0);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }
}
