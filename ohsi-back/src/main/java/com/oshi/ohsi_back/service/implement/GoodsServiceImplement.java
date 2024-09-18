package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.DeleteGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsListRequestDto;
import com.oshi.ohsi_back.dto.request.goods.SearchGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.UpdateGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsListResponseDto;
import com.oshi.ohsi_back.dto.response.goods.SearchGoodsResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.CategoryRepository.CategoryRepository;
import com.oshi.ohsi_back.repository.GoodsRepositoy.BaseGoodsRepository;
import com.oshi.ohsi_back.repository.OshiRepository.OshiRepository;
import com.oshi.ohsi_back.service.Fileservice;
import com.oshi.ohsi_back.service.GoodsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsServiceImplement implements GoodsService {
    private final UserRepository userRepository;
    private final BaseGoodsRepository baseGoodsRepository;
    private final OshiRepository oshiRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileservice;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddGoodsResponseDto AddGoods(AddGoodsRequestDto dto, String email) {
        log.info("AddGoods method started with user email: {}", email);
        log.info("Received DTO: {}", dto);

        List<MultipartFile> files = dto.getFile();

        // 사용자 확인
        log.info("Checking if user exists with email: {}", email);
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }

        // 상품명 중복 확인
        log.info("Checking if goods name '{}' already exists", dto.getName());
        if (baseGoodsRepository.existsByName(dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_GOODS);
        }

        // OshiEntity 및 CategoryEntity 확인
        OshiEntity oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
        if (oshiEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(dto.getCategoryId());
        if (categoryEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        // BaseGoodsEntity 생성
        BaseGoodsEntity baseGoodsEntity = new BaseGoodsEntity(dto, userEntity, oshiEntity, categoryEntity);
        baseGoodsRepository.save(baseGoodsEntity);

        // 이미지 파일 처리
        if (files != null && !files.isEmpty()) {
            log.info("Processing {} image files for goods: {}", files.size(), dto.getName());

            for (MultipartFile file : files) {
                if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().isBlank()) {
                    log.warn("Skipping empty or invalid file");
                    continue;
                }

                String imageUrl = fileservice.SaveImage(file);
                if (imageUrl == null) {
                    throw new CustomException(ErrorCode.VALIDATION_FAILED, "Image saving failed");
                }

                // ImageEntity 생성 및 저장
                ImageEntity imageEntity = new ImageEntity(imageUrl, baseGoodsEntity.getGoodsId(), ImageType.goods);
                imageRepository.save(imageEntity);
            }
        }

        log.info("Successfully added goods with name: {}", dto.getName());
        return new AddGoodsResponseDto(baseGoodsEntity);
    }

    @Override
    public GetGoodsInfoResponseDto GetGoodsInfo(GetGoodsInfoRequsetDto dto) {
        boolean existsGoods = baseGoodsRepository.existsByGoodsId(dto.getGoodsId());
        if (!existsGoods) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findByGoodsId(dto.getGoodsId());
        List<ImageEntity> images = imageRepository.findByRelatedIdAndRelatedType(dto.getGoodsId(), ImageType.goods);

        
        return new GetGoodsInfoResponseDto(baseGoodsEntity,images);
    }

    @Override
    public SearchGoodsResponseDto Searchgoods(SearchGoodsRequestDto dto) {
        Pageable pageable = PageRequest.of(0, 10);
        SearchGoodsResponseDto result = baseGoodsRepository.searchGoods(dto.getKeyword(), pageable);
        return result;
    }

    @Override
    public GetGoodsListResponseDto GetGoodsList(GetGoodsListRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPagenum(), 10);
        GetGoodsListResponseDto result = baseGoodsRepository.findGoods(dto, pageable);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGoods(UpdateGoodsRequestDto dto, String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }

        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findById(dto.getGoodsId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_BOARD));

        if (!baseGoodsEntity.getWriter().equals(user)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }

        baseGoodsEntity.updateGoods(dto);

        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            for (MultipartFile file : dto.getFile()) {
                if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().isBlank()) {
                    continue;
                }

                String imageUrl = fileservice.SaveImage(file);
                if (imageUrl == null) {
                    throw new CustomException(ErrorCode.VALIDATION_FAILED, "Image saving failed");
                }

                ImageEntity imageEntity = new ImageEntity(imageUrl, baseGoodsEntity.getGoodsId(), ImageType.goods);
                imageRepository.save(imageEntity);
            }
        }

        baseGoodsRepository.save(baseGoodsEntity);
    }

    @Override
    public void deleteGoods(DeleteGoodsRequestDto dto, String email) {
        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findById(dto.getGoodsId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_BOARD));

        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }

        if (baseGoodsEntity.getWriter().getUserId() != user.getUserId()) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }

        baseGoodsRepository.deleteById(dto.getGoodsId());
    }
}