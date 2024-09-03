package com.oshi.ohsi_back.dto.response.category;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetCategoryInfoResponseDto extends ResponseDto {

    private CategoryEntity categoryEntity;

    private GetCategoryInfoResponseDto(CategoryEntity categoryEntity) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.categoryEntity = categoryEntity;
    }

    public static ResponseEntity<? super GetCategoryInfoResponseDto> success(CategoryEntity categoryEntity){
        GetCategoryInfoResponseDto responsebody = new GetCategoryInfoResponseDto(categoryEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto responseBody = new ResponseDto(Responsecode.NOT_EXISTED_BOARD, Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    

    
}
