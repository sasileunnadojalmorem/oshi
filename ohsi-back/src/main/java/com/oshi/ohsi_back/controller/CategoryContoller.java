package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;

import java.nio.charset.StandardCharsets;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequestDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryRequseDto;
import com.oshi.ohsi_back.dto.request.category.SearchCategoryRequestDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.CategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.SearchCategoryResoponseDto;
import com.oshi.ohsi_back.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryContoller {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<? super CategoryResponseDto> AddCategory(
            @ModelAttribute @Valid AddCategoryRequestDto requestbody, 
            @AuthenticationPrincipal String email) {
String name = new String(requestbody.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    log.info("Name after decoding: {}", name);
        ResponseEntity<? super CategoryResponseDto> response = categoryService.AddCategory(requestbody, email);
        return response;
    }
    
    @GetMapping("/get")
    public ResponseEntity<? super GetCategoryResponseDto> getCategoryByOshiId(
            @Valid @RequestBody GetCategoryRequseDto requestbody
        ) {
        ResponseEntity<? super GetCategoryResponseDto> response = categoryService.getcategorybyoshiid(requestbody);
        return response;
    }

    @GetMapping("/info")
    public ResponseEntity<? super GetCategoryInfoResponseDto> getCategoryByOshiInfo(
            @Valid @RequestBody GetCategoryInfoRequsetDto requestbody
        ) {
        ResponseEntity<? super GetCategoryInfoResponseDto> response = categoryService.getCategotyInfo(requestbody);
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<? super SearchCategoryResoponseDto> searchCategoryByOshiId(
            @Valid @RequestBody SearchCategoryRequestDto requestbody
        ) {
        ResponseEntity<? super SearchCategoryResoponseDto> response = categoryService.searchCategory(requestbody);
        return response;
        
        }

}