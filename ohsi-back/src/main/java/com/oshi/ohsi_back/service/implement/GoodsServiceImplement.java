package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.SearchGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.SearchGoodsResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.repository.BaseGoodsRepository;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.OshiRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.Fileservice;
import com.oshi.ohsi_back.service.GoodsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsServiceImplement implements GoodsService{
    private final UserRepository userRepository;
    private final BaseGoodsRepository baseGoodsRepository;
    private final OshiRepository oshiRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileservice;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<? super AddGoodsResponseDto> AddGoods(AddGoodsRequestDto dto, String email) {
        log.info("AddGoods method started with user email: {}", email);
        log.info("Received DTO: {}", dto);  // 추가된 로깅

        ImageEntity imageEntity = null;
        String imageUrl = null;
        MultipartFile file = dto.getFile();
        
        try {
            // 사용자 확인
            log.info("Checking if user exists with email: {}", email);
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) {
                log.warn("User with email {} not found", email);
                return AddGoodsResponseDto.notExistUser();
            }
    
            // 상품명 중복 확인
            log.info("Checking if goods name '{}' already exists", dto.getName());
            boolean existsName = baseGoodsRepository.existsByName(dto.getName());
            if (existsName) {
                log.warn("Goods name '{}' already exists", dto.getName());
                return AddGoodsResponseDto.duplicationName();
            }
    
            // OshiEntity 및 CategoryEntity 확인
            log.info("Fetching OshiEntity with ID: {}", dto.getOshiId());
            OshiEntity oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
            if (oshiEntity == null) {
                log.error("OshiEntity with ID {} not found", dto.getOshiId());
                return AddGoodsResponseDto.validateFailed();  // 구체적인 에러 메시지 추가
            }
            
            log.info("Fetching CategoryEntity with ID: {}", dto.getCategoryId());
            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(dto.getCategoryId());
            if (categoryEntity == null) {
                log.error("CategoryEntity with ID {} not found", dto.getCategoryId());
                return AddGoodsResponseDto.databaseError();  // 구체적인 에러 메시지 추가
            }
    
            // BaseGoodsEntity 생성
            log.info("Creating BaseGoodsEntity with name: {}", dto.getName());
            BaseGoodsEntity baseGoodsEntity = new BaseGoodsEntity(dto, userEntity, oshiEntity, categoryEntity);
    
            // 파일이 있는 경우 이미지 저장 처리
            if (file != null && !file.isEmpty()) {
                log.info("Saving image file for goods: {}", dto.getName());
                // 먼저 파일을 저장
                imageUrl = fileservice.SaveImage(file);
                if (imageUrl == null) {
                    log.error("Image saving failed for goods: {}", dto.getName());
                    throw new RuntimeException("Image saving failed");
                }
    
                // 파일 저장에 성공한 경우에만 ImageEntity 생성
                imageEntity = new ImageEntity();
                imageEntity.setUrl(imageUrl);
                imageRepository.save(imageEntity);
    
                // BaseGoodsEntity에 이미지 연결
                baseGoodsEntity.setImage(imageEntity);
            }
    
            // BaseGoodsEntity 저장
            log.info("Saving BaseGoodsEntity for goods: {}", dto.getName());
            baseGoodsRepository.save(baseGoodsEntity);
    
            // 성공 응답과 함께 추가된 상품 정보 반환
            log.info("Successfully added goods with name: {}", dto.getName());
            return AddGoodsResponseDto.success();
    
        } catch (Exception e) {
            log.error("Error occurred while adding goods: {}", dto.getName(), e);
            return AddGoodsResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetGoodsResponseDto> GetGoods(GetGoodsRequestDto dto) {
        int id = dto.getId();
        int page_size = 30;
        Sort sortOrder;
        Page<BaseGoodsEntity> page = null;

        try {
            sortOrder = "recent".equals(dto.getSortOrder()) 
                                ? Sort.by("goodsId").descending() // goods_id를 goodsId로 수정
                                : Sort.by("goodsId").ascending();

            Pageable pageable = PageRequest.of(dto.getPagenum(), page_size, sortOrder);

            if ("OSHI".equals(dto.getMethod())) {
                boolean existsOshi = oshiRepository.existsById(id);
                if (!existsOshi) return GetGoodsResponseDto.notExistBoard();
                page = baseGoodsRepository.findByOshi_OshiId(id, pageable); // findByOshiId 사용
            } else if ("CATEGORY".equals(dto.getMethod())) {
                boolean existsCategory = categoryRepository.existsById(id);
                if (!existsCategory) return GetGoodsResponseDto.notExistBoard();
                page = baseGoodsRepository.findByCategory_CategoryId(id, pageable); // findByCategoryId 사용
            } else {
                return GetGoodsResponseDto.databaseError();
                 // 유효하지 않은 method 처리
            }

            return GetGoodsResponseDto.success(page);
        } catch (Exception e) {
            e.printStackTrace();
            return GetGoodsResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetGoodsInfoResponseDto> GetGoodsInfo(GetGoodsInfoRequsetDto dto) {
       try {
        boolean existsGoods = baseGoodsRepository.existsByGoodsId(dto.getGoodsId());
        if(!existsGoods) return GetGoodsInfoResponseDto.notExistBoard();
        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findByGoodsId(dto.getGoodsId());
        return GetGoodsInfoResponseDto.success(baseGoodsEntity);
       
    
    } catch (Exception e) {
        e.printStackTrace();
        return GetGoodsInfoResponseDto.databaseError();    // DB 에러
       }
    }

    @Override
    public ResponseEntity<? super SearchGoodsResponseDto> Searchgoods(SearchGoodsRequestDto dto) {
        try {

            List<BaseGoodsEntity> baseGoodsEntities = baseGoodsRepository.searchGoodsList(dto.getKeyword(),10);
            return SearchGoodsResponseDto.success(baseGoodsEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return SearchGoodsResponseDto.databaseError();

        }
    }
}