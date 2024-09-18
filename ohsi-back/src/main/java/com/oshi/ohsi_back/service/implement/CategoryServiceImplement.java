package com.oshi.ohsi_back.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequestDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryRequseDto;
import com.oshi.ohsi_back.dto.request.category.SearchCategoryRequestDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.CategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.SearchCategoryResoponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.OshiRepository.OshiRepository;
import com.oshi.ohsi_back.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplement implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final OshiRepository oshiRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryResponseDto AddCategory(AddCategoryRequestDto dto, String email) {
        // OshiEntity 확인
        if (!oshiRepository.existsById(dto.getOshiId())) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        // 카테고리 이름 중복 확인
        if (categoryRepository.existsByName(dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_CATEGORY);
        }

        // CategoryEntity 생성 및 저장
        CategoryEntity categoryEntity = new CategoryEntity(dto);
        categoryRepository.save(categoryEntity);
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto(categoryEntity);
        return CategoryResponseDto;
    }

    @Override
    public GetCategoryInfoResponseDto getCategotyInfo(GetCategoryInfoRequsetDto dto) {
        // 카테고리 정보 확인
        CategoryEntity categoryEntity = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_BOARD));

        return GetCategoryInfoResponseDto.success(categoryEntity);
    }

    @Override
    public GetCategoryResponseDto getcategorybyoshiid(GetCategoryRequseDto dto) {
        // 특정 OshiId에 대한 카테고리 목록 가져오기
        List<CategoryEntity> categories = categoryRepository.findByOshiId(dto.getOshiid());
        if (categories.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        return GetCategoryResponseDto.success(categories);
    }

    @Override
    public SearchCategoryResoponseDto searchCategory(SearchCategoryRequestDto dto) {
        // 카테고리 검색 처리 로직 (예시)
        List<CategoryEntity> categories = categoryRepository.searchByKeyword(dto.getKeyword());
        if (categories.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        return SearchCategoryResoponseDto.success(categories);
    }
}