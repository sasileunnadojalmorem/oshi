package com.oshi.ohsi_back.service;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryRequseDto;
import com.oshi.ohsi_back.dto.request.category.SearchCategoryRequestDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.SearchCategoryResoponseDto;

public interface CategoryService {

    public ResponseEntity<? super AddCategoryResponseDto> AddCategory(AddCategoryRequsetDto dto,String email);
    public ResponseEntity<? super GetCategoryResponseDto> getcategorybyoshiid(GetCategoryRequseDto dto);
    public ResponseEntity<? super GetCategoryInfoResponseDto> getCategotyInfo(GetCategoryInfoRequsetDto dto);
    public ResponseEntity<? super SearchCategoryResoponseDto> searchCategory(SearchCategoryRequestDto dto);
    
}
