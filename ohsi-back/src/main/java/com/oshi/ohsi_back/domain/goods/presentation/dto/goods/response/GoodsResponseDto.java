package com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.presentation.dto.response.ImageDto;

import lombok.Getter;

@Getter
public class GoodsResponseDto {
    private int oshiId;
    private String oshiName;
    private int categoryId;
    private String categoryName;
    private int goodsId;
    private String name;
    private String description;
    private int viewCount;
    private int favoriteCount;
    private List<ImageDto> images;
    
    public GoodsResponseDto(BaseGoodsEntity baseGoodsEntity){
        this.oshiId = baseGoodsEntity.getOshi().getOshiId();
        this.oshiName = baseGoodsEntity.getOshi().getName();
        this.categoryId = baseGoodsEntity.getCategory().getCategoryId();
        this.categoryName = baseGoodsEntity.getCategory().getName();
        this.goodsId = baseGoodsEntity.getGoodsId();
        this.name = baseGoodsEntity.getName();
        this.description = baseGoodsEntity.getDescription();
        this.viewCount = baseGoodsEntity.getViewCount();
        this.favoriteCount = baseGoodsEntity.getFavoriteCount();
    }

    public GoodsResponseDto(BaseGoodsEntity baseGoodsEntity, List<ImageEntity> imageEntities) {
        this.oshiId = baseGoodsEntity.getOshi().getOshiId();
        this.oshiName = baseGoodsEntity.getOshi().getName();
        this.categoryId = baseGoodsEntity.getCategory().getCategoryId();
        this.categoryName = baseGoodsEntity.getCategory().getName();
        this.goodsId = baseGoodsEntity.getGoodsId();
        this.name = baseGoodsEntity.getName();
        this.description = baseGoodsEntity.getDescription();
        this.viewCount = baseGoodsEntity.getViewCount();
        this.favoriteCount = baseGoodsEntity.getFavoriteCount();

        // ImageEntity 리스트를 ImageDto 리스트로 변환하며 null 체크를 추가
        this.images = imageEntities == null 
                      ? Collections.emptyList() 
                      : imageEntities.stream()
                                     .filter(imageEntity -> imageEntity != null)
                                     .map(imageEntity -> new ImageDto(imageEntity))
                                     .collect(Collectors.toList());
    }
}