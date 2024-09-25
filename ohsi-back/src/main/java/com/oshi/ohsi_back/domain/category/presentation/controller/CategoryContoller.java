package com.oshi.ohsi_back.domain.category.presentation.controller;

import jakarta.validation.Valid;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.domain.category.application.CategoryService;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.AddCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryListRequseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.SearchCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.CategoryResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryListResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.SearchCategoryResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryContoller {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public CategoryResponseDto AddCategory(
            @ModelAttribute @Valid AddCategoryRequestDto requestbody, 
            @AuthenticationPrincipal String email) {

        CategoryResponseDto response = categoryService.AddCategory(requestbody, email);
        return response;
    }
    
    @GetMapping("/list")
    public GetCategoryListResponseDto getCategoryByOshiId(
            @Valid @RequestBody GetCategoryListRequseDto requestbody
        ) {
        GetCategoryListResponseDto response = categoryService.getcategorybyoshiid(requestbody);
        return response;
    }

    @GetMapping("/info")
    public GetCategoryInfoResponseDto getCategoryByOshiInfo(
            @Valid @RequestBody GetCategoryInfoRequsetDto requestbody
        ) {
        GetCategoryInfoResponseDto response = categoryService.getCategotyInfo(requestbody);
        return response;
    }

    @GetMapping("/search")
    public SearchCategoryResponseDto searchCategoryByOshiId(
            @Valid @RequestBody SearchCategoryRequestDto requestbody
        ) {
        SearchCategoryResponseDto response = categoryService.searchCategory(requestbody);
        return response;
        
        }

}