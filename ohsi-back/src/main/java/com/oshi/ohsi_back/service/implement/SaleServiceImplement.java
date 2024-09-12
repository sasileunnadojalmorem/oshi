package com.oshi.ohsi_back.service.implement;



import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.DeleteSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
import com.oshi.ohsi_back.dto.request.sale.UpdateSaleRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleListResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.SaleResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.entity.SaleEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.exception.SaleException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.service.Fileservice;
import com.oshi.ohsi_back.service.SaleService;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.SaleRepository.SaleRepository;
import com.oshi.ohsi_back.repository.BaseGoodsRepository;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.OshiRepository;

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
    @Transactional(readOnly = true)  // ��기 전용 ������션
    public GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto) {
        int id = dto.getSaleId();
        try {
            SaleEntity saleEntity = saleRepository.findBySalesId(id)
                .orElseThrow(() -> new SaleException(ErrorCode.NOT_EXISTED_SALES));  // SaleEntity가 없으면 예외 발생
    
            // SaleEntity를 받아서 DTO로 변환
            return GetSaleInfoResponseDto.createFromSaleEntity(saleEntity);
    
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 null 반환 또는 적절한 예외 처리
            return null;
        }
    }
    @Override
    @Transactional(readOnly = true)
    public GetSaleListResponseDto getSaleList(GetSaleListRequestDto dto) {
    int pageSize = 10;
    try {
        Pageable pageable = PageRequest.of(dto.getPagenum(), pageSize);
        Page<SaleResponseDto> response = saleRepository.getSaleList(pageable, dto);
        // Page<SaleResponseDto>를 받아서 DTO로 변환
        return GetSaleListResponseDto.builder()
           .saleEntities(response.getContent())   // 리스트를 가져옴
           .totalPages(response.getTotalPages())  // 전체 페이지 수
           .totalCount((int)response.getTotalElements())  // 전체 요소 수
           .build();
    } catch (Exception e) {
        e.printStackTrace();
        return null;  // 예외 발생 시 null 반환 또는 적절한 에러 처리
    }
}

    @Override
    @Transactional(rollbackFor = Exception.class)  // ��기 전용 ������션
    public void deleteSale(DeleteSaleRequestDto dto, String email) {
            UserEntity user = userRepository.findByEmail(email);
            if (user == null) {
                throw new SaleException(ErrorCode.NOT_EXISTED_USER);
            }
            SaleEntity saleEntity = saleRepository.findBySalesId(dto.getSaleId())
            .orElseThrow(()-> new SaleException(ErrorCode.NOT_EXISTED_SALES));
            if( saleEntity.getUser().getUserId() == user.getUserId()) saleRepository.deleteById(dto.getSaleId());
            return; 
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSale(UpdateSaleRequestDto dto, String email) {
    MultipartFile file = dto.getFile();
    String imageUrl;
    ImageEntity imageEntity = null; // 초기화
    try {
        // 사용자와 판매 정보 조회
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new SaleException(ErrorCode.NOT_EXISTED_USER);
        }
        SaleEntity saleEntity = saleRepository.findBySalesId(dto.getSaleId())
                .orElseThrow(() -> new SaleException(ErrorCode.NOT_EXISTED_SALES));

        // 권한 확인 (사용자가 판매자와 일치하는지 확인)
        if (!saleEntity.getUser().equals(user)) {
            throw new SaleException(ErrorCode.NOT_EXISTED_USER, "User is not authorized to update this sale");
        }

        // 이미지 파일이 있을 때만 처리
        if (file != null && !file.isEmpty()) {
            // 파일 저장
            imageUrl = fileservice.SaveImage(file);
            if (imageUrl == null) {
                throw new SaleException(ErrorCode.VALIDATION_FAILED, "Image saving failed");
            }

            // 새로운 이미지 엔터티 생성
            imageEntity = new ImageEntity();
            imageEntity.setUrl(imageUrl);
            imageRepository.save(imageEntity);
            
            // 판매 엔터티에 이미지 연결
            saleEntity.setImage(imageEntity);
        }

        // 판매 엔터티 업데이트
        saleEntity.updateSale(dto);

        // 변경사항 저장
        saleRepository.save(saleEntity);

    } catch (Exception e) {
        e.printStackTrace();
        // 예외 처리 및 로깅
        throw new SaleException(ErrorCode.DATABASE_ERROR);  // 적절한 예외 던지기
    }
 
    }
  
  

    
}
