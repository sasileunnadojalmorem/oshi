package com.oshi.ohsi_back.domain.category.application;


import com.oshi.ohsi_back.domain.category.presentation.dto.request.AddCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryListRequseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.SearchCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.CategoryResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryListResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.SearchCategoryResponseDto;

public interface CategoryService {

    public CategoryResponseDto AddCategory(AddCategoryRequestDto dto,String email);
    public GetCategoryListResponseDto getcategorybyoshiid(GetCategoryListRequseDto dto);
    public GetCategoryInfoResponseDto getCategotyInfo(GetCategoryInfoRequsetDto dto);
    public SearchCategoryResponseDto searchCategory(SearchCategoryRequestDto dto);
    
}
