package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.SaleResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.entity.SaleEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.service.Fileservice;
import com.oshi.ohsi_back.service.SaleService;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.BaseGoodsRepository;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.OshiRepository;
import com.oshi.ohsi_back.repository.SaleRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImplement implements SaleService {
    
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;
    private final OshiRepository oshiRepository;
    private final CategoryRepository categoryRepository;
    private final BaseGoodsRepository baseGoodsRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileservice;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, String email) {
        MultipartFile file = dto.getFile();
        String imageUrl = null;
        ImageEntity imageEntity = null;
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return AddSaleResponseDto.notExistUser();
            OshiEntity oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(dto.getCategoryId());
            BaseGoodsEntity baseGoodsEntity  = baseGoodsRepository.findByGoodsId(dto.getGoodsId());
            if(oshiEntity == null) return AddSaleResponseDto.databaseError();
            if(categoryEntity == null) return AddSaleResponseDto.databaseError();
            if(baseGoodsEntity == null) return AddSaleResponseDto.databaseError();
            SaleEntity saleEntity = new SaleEntity(dto,userEntity,oshiEntity,categoryEntity,baseGoodsEntity);
            if (file != null && !file.isEmpty()) {
                // 먼저 파일을 저장
                imageUrl = fileservice.SaveImage(file);
                if (imageUrl == null) {
                    throw new RuntimeException("Image saving failed");
                }
                // 파일 저장에 성공한 경우에만 ImageEntity 생성
                imageEntity = new ImageEntity();
                imageEntity.setUrl(imageUrl);
                imageRepository.save(imageEntity);
                // BaseGoodsEntity에 이미지 연결
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
    public GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto) {
        int id = dto.getSaleId();

        try {
            SaleEntity saleEntity = saleRepository.findBySalesId(id);

            // SaleEntity를 받아서 DTO로 변환
            return GetSaleInfoResponseDto.createFromSaleEntity(saleEntity);

        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 추가 (null 반환 또는 적절한 예외 처리)
            return null;
        }
    }
  
  

    
}
