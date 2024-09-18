package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.category.AddCategoryRequestDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryListRequseDto;
import com.oshi.ohsi_back.dto.request.category.SearchCategoryRequestDto;
import com.oshi.ohsi_back.dto.response.category.CategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryListResponseDto;
import com.oshi.ohsi_back.dto.response.category.SearchCategoryResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.CategoryRepository.CategoryRepository;
import com.oshi.ohsi_back.repository.OshiRepository.OshiRepository;
import com.oshi.ohsi_back.service.CategoryService;
import com.oshi.ohsi_back.service.Fileservice;

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
    public CategoryResponseDto AddCategory(AddCategoryRequestDto dto, String email) {
        // OshiEntity 확인
        CategoryEntity categoryEntity = null;
        ImageEntity imageEntity = null;
        String imageUrl = null;
        MultipartFile file = dto.getFile();
        if (!oshiRepository.existsById(dto.getOshiId())) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        // 카테고리 이름 중복 확인
        if (categoryRepository.existsByName(dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_CATEGORY);
        }

        // CategoryEntity 생성 및 저장
        categoryEntity = new CategoryEntity(dto);
        categoryRepository.save(categoryEntity);
        if (file != null && !file.isEmpty()) {

                // 2-1. 이미지 엔티티 미리 저장
                imageEntity = new ImageEntity();
                imageEntity.setUrl("temporary-url");  // 임시 URL 설정
                imageEntity.setRelatedId(categoryEntity.getCategoryId());  // 오시 ID를 related_id에 설정
                imageEntity.setRelatedType(ImageType.category);  // 관련 엔티티 타입을 OSHI로 설정
                imageRepository.save(imageEntity);  // 이미지 엔티티를 먼저 저장

                // 트랜잭션 내에서 파일 저장을 시도
                imageUrl = fileService.SaveImage(file);
                if (imageUrl == null) {
                    throw new RuntimeException("Image saving failed");  // 파일 저장 실패 시 예외 발생
                }

                // 2-2. 이미지 엔티티의 URL 필드 갱신
                imageEntity.setUrl(imageUrl);
                imageRepository.save(imageEntity);  // URL 업데이트
            }
        CategoryResponseDto response = CategoryResponseDto.builder().
                imageUrl(imageUrl)
                .categoryEntity(categoryEntity)
                .build();

        return response;
    }

    @Override
    public GetCategoryInfoResponseDto getCategotyInfo(GetCategoryInfoRequsetDto dto) {
        // 카테고리 정보 확인
        CategoryEntity categoryEntity = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_BOARD));
    
        // 이미지 조회
        List<ImageEntity> imageEntities = imageRepository.findByRelatedIdAndRelatedType(dto.getCategoryId(), ImageType.category);
        
        // 이미지가 없을 경우 null로 처리
        String imageUrl = (imageEntities.isEmpty()) ? null : imageEntities.get(0).getUrl();
    
        // GetCategoryInfoResponseDto 생성
        return GetCategoryInfoResponseDto.builder()
                .imageUrl(imageUrl)
                .categoryEntity(categoryEntity)
                .build();
        }

    @Override
    public GetCategoryListResponseDto getcategorybyoshiid(GetCategoryListRequseDto dto) {
        int pazeSize = 10;
        Pageable pageable = PageRequest.of(dto.getPagenum(), pazeSize);
        // 특정 OshiId에 대한 카테고리 목록 가져오기
        return categoryRepository.GetCategoryList(dto,pageable);
    }

    @Override
    public SearchCategoryResponseDto searchCategory(SearchCategoryRequestDto dto) {
        Pageable pageable = PageRequest.of(0, 10);
        // 카테고리 검색 처리 로직 (예시)
        Page<CategoryResponseDto> response = categoryRepository.searchCategory(dto.getKeyword(),pageable);
        return SearchCategoryResponseDto.builder().category(response).build();
    }
}