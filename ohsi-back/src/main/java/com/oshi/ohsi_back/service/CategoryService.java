package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequsetDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;

public interface CategoryService {

    public ResponseEntity<? super AddCategoryResponseDto> AddCategory(AddCategoryRequsetDto dto,String email);
    
}
