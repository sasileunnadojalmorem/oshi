package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequestDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryListRequseDto;
import com.oshi.ohsi_back.dto.request.category.SearchCategoryRequestDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.CategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryListResponseDto;
import com.oshi.ohsi_back.dto.response.category.SearchCategoryResponseDto;

public interface CategoryService {

    public CategoryResponseDto AddCategory(AddCategoryRequestDto dto,String email);
    public GetCategoryListResponseDto getcategorybyoshiid(GetCategoryListRequseDto dto);
    public GetCategoryInfoResponseDto getCategotyInfo(GetCategoryInfoRequsetDto dto);
    public SearchCategoryResponseDto searchCategory(SearchCategoryRequestDto dto);
    
}
