package com.oshi.ohsi_back.dto.response.category;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;

import lombok.Getter;


@Getter
public class AddCategoryResponseDto extends ResponseDto {

    private AddCategoryResponseDto(CategoryEntity categoryEntity){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);

    } 

    public static ResponseEntity<? super AddCategoryResponseDto> success(CategoryEntity categoryEntity){
    AddCategoryResponseDto responseDto = new AddCategoryResponseDto(categoryEntity);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> duplicationName() {
        ResponseDto result = new ResponseDto(Responsecode.DUPLICATE_NAME, Responsemessage.DUPLICATE_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responsebody = new ResponseDto(Responsecode.NOT_EXISTED_USER,Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);
    }
    
}
