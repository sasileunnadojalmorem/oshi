package com.oshi.ohsi_back.domain.category.presentation.dto.response;


import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;

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
