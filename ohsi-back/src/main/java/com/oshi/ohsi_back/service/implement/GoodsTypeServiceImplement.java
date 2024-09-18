package com.oshi.ohsi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.response.goodsType.GetGoodsTypeResponseDto;
import com.oshi.ohsi_back.dto.response.goodsType.GoodsTypeDto;
import com.oshi.ohsi_back.repository.GoodsTypeRepository;
import com.oshi.ohsi_back.service.GoodsTypeService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsTypeServiceImplement implements GoodsTypeService {

    private final GoodsTypeRepository goodsTypeRepository;

    @Override
    public ResponseEntity<GetGoodsTypeResponseDto> getGoodstypeList() {
        // Fetching list of GoodsTypeDto from repository
        List<GoodsTypeDto> response = goodsTypeRepository.ShowGoods();
        
        // Returning the list wrapped in GetGoodsTypeResponseDto
        return ResponseEntity.ok(
            GetGoodsTypeResponseDto.builder()
                .dto(response) // Assuming the builder requires 'dto' as the key for GoodsTypeDto list
                .build()
        );
    }
}