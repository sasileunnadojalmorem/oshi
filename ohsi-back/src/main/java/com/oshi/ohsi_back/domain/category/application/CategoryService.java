package com.oshi.ohsi_back.domain.category.application;

import com.oshi.ohsi_back.domain.category.presentation.dto.request.AddCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryListRequseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.SearchCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.CategoryResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryListResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.SearchCategoryResponseDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

public interface CategoryService {

    CategoryResponseDto addCategory(AddCategoryRequestDto dto, UserEntity user);
    GetCategoryListResponseDto getCategoryByOshiId(GetCategoryListRequseDto dto);
    GetCategoryInfoResponseDto getCategoryInfo(GetCategoryInfoRequsetDto dto);
    SearchCategoryResponseDto searchCategory(SearchCategoryRequestDto dto);
}