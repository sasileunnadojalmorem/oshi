package com.oshi.ohsi_back.dto.response.oshi;

import com.oshi.ohsi_back.entity.OshiEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OshiResponseDto {

    private String imageUrl;
    private int oshiId;
    private String description;
    private String name;

    @Builder
    public OshiResponseDto(String imageUrl, OshiEntity oshiEntity) {
        this.oshiId = oshiEntity.getOshiId();
        this.name = oshiEntity.getName();
        this.description = oshiEntity.getDescription();
        this.imageUrl = imageUrl;  // 이미지가 없으면 null로 처리
    }
}