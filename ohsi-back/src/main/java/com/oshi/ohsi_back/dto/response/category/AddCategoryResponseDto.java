package com.oshi.ohsi_back.dto.response.category;


import com.oshi.ohsi_back.entity.CategoryEntity;

import lombok.Builder;
import lombok.Getter;


@Getter
public class AddCategoryResponseDto {

    private CategoryResponseDto dto;
    @Builder
    public AddCategoryResponseDto(CategoryEntity categoryEntity){
        this.dto = CategoryResponseDto.builder().categoryEntity(categoryEntity).build();
    } 

    
}
