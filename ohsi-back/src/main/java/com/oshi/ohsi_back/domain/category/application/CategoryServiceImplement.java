package com.oshi.ohsi_back.domain.category.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;
import com.oshi.ohsi_back.domain.category.infrastructure.CategoryRepository;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.AddCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryListRequseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.SearchCategoryRequestDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.CategoryResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryListResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.SearchCategoryResponseDto;
import com.oshi.ohsi_back.domain.image.application.Fileservice;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.oshi.ohsi_back.domain.image.infrastructure.ImageRepository;
import com.oshi.ohsi_back.domain.ohsi.infrastructure.OshiRepository;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImplement implements CategoryService {

    private final Fileservice fileService;
    private final CategoryRepository categoryRepository;
    private final OshiRepository oshiRepository;
    private final ImageRepository imageRepository;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryResponseDto addCategory(AddCategoryRequestDto dto, UserEntity user) {
        CategoryEntity categoryEntity;
        ImageEntity imageEntity = null;
        String imageUrl = null;
        MultipartFile file = dto.getFile();

        if (!oshiRepository.existsById(dto.getOshiId())) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        if (categoryRepository.existsByName(dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_CATEGORY);
        }

        categoryEntity = new CategoryEntity(dto);
        categoryRepository.save(categoryEntity);

        if (file != null && !file.isEmpty()) {
            imageEntity = new ImageEntity();
            imageEntity.setUrl("temporary-url");
            imageEntity.setRelatedId(categoryEntity.getCategoryId());
            imageEntity.setRelatedType(ImageType.category);
            imageRepository.save(imageEntity);

            imageUrl = fileService.SaveImage(file);
            if (imageUrl == null) {
                throw new RuntimeException("Image saving failed");
            }

            imageEntity.setUrl(imageUrl);
            imageRepository.save(imageEntity);
        }

        return CategoryResponseDto.builder()
                .imageUrl(imageUrl)
                .categoryEntity(categoryEntity)
                .build();
    }

    @Override
    public GetCategoryInfoResponseDto getCategoryInfo(GetCategoryInfoRequsetDto dto) {
        CategoryEntity categoryEntity = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_BOARD));
    
        List<ImageEntity> imageEntities = imageRepository.findByRelatedIdAndRelatedType(dto.getCategoryId(), ImageType.category);
        String imageUrl = (imageEntities.isEmpty()) ? null : imageEntities.get(0).getUrl();
    
        return GetCategoryInfoResponseDto.builder()
                .imageUrl(imageUrl)
                .categoryEntity(categoryEntity)
                .build();
    }

    @Override
    public GetCategoryListResponseDto getCategoryByOshiId(GetCategoryListRequseDto dto) {
        Pageable pageable = PageRequest.of(dto.getPagenum(), 10);
        return categoryRepository.GetCategoryList(dto, pageable);
    }
    @Override
    public SearchCategoryResponseDto searchCategory(SearchCategoryRequestDto dto) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CategoryResponseDto> response = categoryRepository.searchCategory(dto.getKeyword(), pageable);
        return SearchCategoryResponseDto.builder().category(response).build();
    }
}