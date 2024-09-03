package com.oshi.ohsi_back.dto.response.category;


import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class GetCategoryResponseDto extends ResponseDto {

    private Page<CategoryEntity> categorylist ;

    private GetCategoryResponseDto (Page<CategoryEntity> categoryLists){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.categorylist = categoryLists;
        
    }
    public static ResponseEntity<? super GetCategoryResponseDto> success(Page<CategoryEntity> categotylist){
        GetCategoryResponseDto responsebody = new GetCategoryResponseDto(categotylist);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }
    public static ResponseEntity<ResponseDto> notExistBoard() {
            ResponseDto responseBody = new ResponseDto(Responsecode.NOT_EXISTED_BOARD, Responsemessage.NOT_EXISTED_BOARD);
            return ResponseEntity.status(401).body(responseBody);
    }
    
    

}
