package com.oshi.ohsi_back.domain.goods.application;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;
import com.oshi.ohsi_back.domain.category.infrastructure.CategoryRepository;
import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.domain.goods.infrastructue.GoodsRepositoy.BaseGoodsRepository;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.*;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.*;
import com.oshi.ohsi_back.domain.image.application.Fileservice;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.oshi.ohsi_back.domain.image.infrastructure.ImageRepository;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;
import com.oshi.ohsi_back.domain.ohsi.infrastructure.OshiRepository;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsServiceImplement implements GoodsService {

    private final BaseGoodsRepository baseGoodsRepository;
    private final OshiRepository oshiRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileservice;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddGoodsResponseDto addGoods(AddGoodsRequestDto dto, UserEntity user) {
        log.info("AddGoods method started with user email: {}", user.getEmail());
        List<MultipartFile> files = dto.getFile();

        if (baseGoodsRepository.existsByName(dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_GOODS);
        }

        OshiEntity oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(dto.getCategoryId());

        BaseGoodsEntity baseGoodsEntity = new BaseGoodsEntity(dto, user, oshiEntity, categoryEntity);
        baseGoodsRepository.save(baseGoodsEntity);

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
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
        return new AddGoodsResponseDto(baseGoodsEntity);
    }

    @Override
    public GetGoodsInfoResponseDto getGoodsInfo(GetGoodsInfoRequsetDto dto) {
        boolean existsGoods = baseGoodsRepository.existsByGoodsId(dto.getGoodsId());
        if (!existsGoods) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findByGoodsId(dto.getGoodsId());
        List<ImageEntity> images = imageRepository.findByRelatedIdAndRelatedType(dto.getGoodsId(), ImageType.goods);

        return new GetGoodsInfoResponseDto(baseGoodsEntity, images);
    }

    @Override
    public SearchGoodsResponseDto searchGoods(SearchGoodsRequestDto dto) {
        Pageable pageable = PageRequest.of(0, 10);
        return baseGoodsRepository.searchGoods(dto.getKeyword(), pageable);
    }

    @Override
    public GetGoodsListResponseDto getGoodsList(GetGoodsListRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPagenum(), 10);
        return baseGoodsRepository.findGoods(dto, pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGoods(UpdateGoodsRequestDto dto, UserEntity user) {
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
    public void deleteGoods(DeleteGoodsRequestDto dto, UserEntity user) {
        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findById(dto.getGoodsId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_BOARD));

        if (!baseGoodsEntity.getWriter().equals(user)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }

        baseGoodsRepository.deleteById(dto.getGoodsId());
    }
}