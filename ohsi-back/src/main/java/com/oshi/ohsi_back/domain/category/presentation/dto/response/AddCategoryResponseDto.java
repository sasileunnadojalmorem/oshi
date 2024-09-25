
package com.oshi.ohsi_back.domain.category.presentation.dto.response;


import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;

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
