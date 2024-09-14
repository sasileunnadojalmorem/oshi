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
    private Integer status;

    @Builder
    public OshiResponseDto(String imageUrl, OshiEntity oshiEntity, Integer status) {
        this.oshiId = oshiEntity.getOshiId();
        this.name = oshiEntity.getName();
        this.description = oshiEntity.getDescription();
        this.imageUrl = imageUrl;
        this.status = status;
    }

    // Overloaded constructor without status (used when status is not needed)
    public OshiResponseDto(OshiEntity oshiEntity) {
        this.oshiId = oshiEntity.getOshiId();
        this.name = oshiEntity.getName();
        this.description = oshiEntity.getDescription();
        this.imageUrl = null;
        this.status = 0; // default value for status when not provided
    }
}