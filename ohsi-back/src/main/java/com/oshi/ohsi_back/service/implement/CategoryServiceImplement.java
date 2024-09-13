package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryRequseDto;
import com.oshi.ohsi_back.dto.request.category.SearchCategoryRequestDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryInfoResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.SearchCategoryResoponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.OshiRepository.OshiRepository;
import com.oshi.ohsi_back.service.CategoryService;
import com.oshi.ohsi_back.service.Fileservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor    
public class CategoryServiceImplement  implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final OshiRepository oshiRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileservice;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<? super AddCategoryResponseDto> AddCategory(AddCategoryRequsetDto dto, String email) {
        CategoryEntity categoryEntity = null;
        ImageEntity imageEntity = null;
        String imageUrl = null;
        MultipartFile file = dto.getFile();
        OshiEntity oshiEntity =  null;
    
        try {
            // 사용자 및 카테고리 이름 중복 확인
            boolean existsUser = userRepository.existsByEmail(email);
            boolean existsName = categoryRepository.existsByName(dto.getName());
            if (!existsUser) return AddCategoryResponseDto.notExistUser();
            if (existsName) return AddCategoryResponseDto.duplicationName();
            
            oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
            if (oshiEntity == null) {
                // 상세 로그를 추가하여 어떤 이유로 오류가 발생하는지 확인
                return AddCategoryResponseDto.validateFailed();
            }
    
            // 카테고리 생성 및 이미지 처리
            categoryEntity = new CategoryEntity(dto, oshiEntity);
    
            if (file != null && !file.isEmpty()) {
                imageEntity = new ImageEntity();
                imageEntity.setUrl("temporary-url");
                imageRepository.save(imageEntity);
    
                imageUrl = fileservice.SaveImage(file);
                if (imageUrl == null) {
                    throw new RuntimeException("Image saving failed");
                }
    
                imageEntity.setUrl(imageUrl);
                imageRepository.save(imageEntity);
                categoryEntity.setImage(imageEntity);
            }
    
            // 카테고리 저장
            categoryRepository.save(categoryEntity);
    
            return AddCategoryResponseDto.success(categoryEntity);
    
        } catch (Exception e) {
            e.printStackTrace();
            return AddCategoryResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetCategoryResponseDto> getcategorybyoshiid(GetCategoryRequseDto dto) {
        int oshi_id = dto.getOshiid();
        int page_size = 30;

        try {
            boolean existsOshi = oshiRepository.existsById(dto.getOshiid());
            if (!existsOshi) return GetCategoryResponseDto.notExistBoard();

            Sort sortOrder = "recent".equals(dto.getSortedBy()) 
                                ? Sort.by("categoryId").descending()
                                : Sort.by("categoryId").ascending();

            Pageable pageable = PageRequest.of(dto.getPagenum(), page_size, sortOrder);
            Page<CategoryEntity> categoryEntityList = categoryRepository.findByOshi_OshiId(oshi_id, pageable);

            

            return GetCategoryResponseDto.success(categoryEntityList);

        } catch (Exception e) {
            e.printStackTrace();
            return GetCategoryResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetCategoryInfoResponseDto> getCategotyInfo(GetCategoryInfoRequsetDto dto) {
        try {

            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(dto.getCategoryId());
            if(categoryEntity == null) return GetCategoryInfoResponseDto.validateFailed();
            return GetCategoryInfoResponseDto.success(categoryEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return GetCategoryInfoResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super SearchCategoryResoponseDto> searchCategory(SearchCategoryRequestDto dto) {
        try {
            List<CategoryEntity> categoList = categoryRepository.searchCategory(dto.getKeyword(),10);
            return SearchCategoryResoponseDto.success(categoList);
        } catch (Exception e) {
            e.printStackTrace();
            return SearchCategoryResoponseDto.databaseError();

        }
    }
}