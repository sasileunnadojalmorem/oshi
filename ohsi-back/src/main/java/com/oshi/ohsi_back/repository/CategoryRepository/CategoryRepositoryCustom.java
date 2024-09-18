package com.oshi.ohsi_back.repository.CategoryRepository;

import org.springframework.data.domain.Pageable;

import com.oshi.ohsi_back.dto.request.category.GetCategoryListRequseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryListResponseDto;

public interface CategoryRepositoryCustom {

    GetCategoryListResponseDto GetCategoryList(GetCategoryListRequseDto dto,Pageable pageable);
    
}
