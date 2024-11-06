package com.oshi.ohsi_back.domain.sale.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;
import com.oshi.ohsi_back.domain.category.infrastructure.CategoryRepository;
import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.domain.goods.infrastructue.GoodsRepositoy.BaseGoodsRepository;
import com.oshi.ohsi_back.domain.image.application.Fileservice;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.infrastructure.ImageRepository;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;
import com.oshi.ohsi_back.domain.ohsi.infrastructure.OshiRepository;
import com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity;
import com.oshi.ohsi_back.domain.sale.infrastructure.SaleRepository.SaleRepository;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.*;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.*;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.exception.SaleException;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImplement implements SaleService {
    
    private final SaleRepository saleRepository;
    private final OshiRepository oshiRepository;
    private final CategoryRepository categoryRepository;
    private final BaseGoodsRepository baseGoodsRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileservice;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, UserEntity user) {
        MultipartFile file = dto.getFile();
        String imageUrl = null;
        ImageEntity imageEntity = null;

        try {
            OshiEntity oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(dto.getCategoryId());
            BaseGoodsEntity baseGoodsEntity  = baseGoodsRepository.findByGoodsId(dto.getGoodsId());

            if(oshiEntity == null || categoryEntity == null || baseGoodsEntity == null) {
                return AddSaleResponseDto.databaseError();
            }

            SaleEntity saleEntity = new SaleEntity(dto, user, oshiEntity, categoryEntity, baseGoodsEntity);
            
            if (file != null && !file.isEmpty()) {
                imageUrl = fileservice.SaveImage(file);
                if (imageUrl == null) {
                    throw new RuntimeException("Image saving failed");
                }
                imageEntity = new ImageEntity();
                imageEntity.setUrl(imageUrl);
                imageRepository.save(imageEntity);
                saleEntity.setImage(imageEntity);
            }

            saleRepository.save(saleEntity);
            return AddSaleResponseDto.success(saleEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return AddSaleResponseDto.databaseError();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto) {
        int id = dto.getSaleId();
        SaleEntity saleEntity = saleRepository.findBySalesId(id)
            .orElseThrow(() -> new SaleException(ErrorCode.NOT_EXISTED_SALES));
        
        return GetSaleInfoResponseDto.createFromSaleEntity(saleEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public GetSaleListResponseDto getSaleList(GetSaleListRequestDto dto) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(dto.getPagenum(), pageSize);
        Page<SaleResponseDto> response = saleRepository.getSaleList(pageable, dto);
        
        return GetSaleListResponseDto.builder()
            .saleEntities(response.getContent())
            .totalPages(response.getTotalPages())
            .totalCount((int) response.getTotalElements())
            .currentPage(dto.getPagenum())
            .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSale(DeleteSaleRequestDto dto, UserEntity user) {
        SaleEntity saleEntity = saleRepository.findBySalesId(dto.getSaleId())
                .orElseThrow(() -> new SaleException(ErrorCode.NOT_EXISTED_SALES));

        if (saleEntity.getUser().getUserId() != user.getUserId()) {
            throw new SaleException(ErrorCode.NO_PERMISSION);
        }

        saleRepository.deleteById(dto.getSaleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSale(UpdateSaleRequestDto dto, UserEntity user) {
        MultipartFile file = dto.getFile();
        String imageUrl;
        ImageEntity imageEntity = null;

        SaleEntity saleEntity = saleRepository.findBySalesId(dto.getSaleId())
                .orElseThrow(() -> new SaleException(ErrorCode.NOT_EXISTED_SALES));

        if (!saleEntity.getUser().equals(user)) {
            throw new SaleException(ErrorCode.NO_PERMISSION);
        }

        if (file != null && !file.isEmpty()) {
            imageUrl = fileservice.SaveImage(file);
            if (imageUrl == null) {
                throw new SaleException(ErrorCode.VALIDATION_FAILED, "Image saving failed");
            }

            imageEntity = new ImageEntity();
            imageEntity.setUrl(imageUrl);
            imageRepository.save(imageEntity);
            saleEntity.setImage(imageEntity);
        }

        saleEntity.updateSale(dto);
        saleRepository.save(saleEntity);
    }
}