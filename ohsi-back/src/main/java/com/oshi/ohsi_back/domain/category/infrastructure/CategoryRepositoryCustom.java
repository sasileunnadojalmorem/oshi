package com.oshi.ohsi_back.domain.category.infrastructure;

import org.springframework.data.domain.Pageable;

import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryListRequseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryListResponseDto;

public interface CategoryRepositoryCustom {

    GetCategoryListResponseDto GetCategoryList(GetCategoryListRequseDto dto,Pageable pageable);
    
}
