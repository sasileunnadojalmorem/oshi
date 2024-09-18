package com.oshi.ohsi_back.dto.response.category;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetCategoryInfoResponseDto {

    private CategoryResponseDto categoryResponseDto;
    
    
    @Builder
    public GetCategoryInfoResponseDto(String imageUrl, CategoryEntity categoryEntity) {
        this.categoryResponseDto = new CategoryResponseDto(imageUrl, categoryEntity);
    }
    

    
}
