package com.oshi.ohsi_back.dto.response.image;

import java.util.List;

import com.oshi.ohsi_back.entity.ImageEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageDto {
    private int imageId;
    private String imageUrl;

    @Builder 
    public ImageDto(ImageEntity imageEntity){
        this.imageId = imageEntity.getId();
        this.imageUrl = imageEntity.getUrl();
    }
}
