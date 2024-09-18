package com.oshi.ohsi_back.dto.response.category;

import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.CategoryEntity.CategoryType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private String imageUrl;
    private int oshiId;
    private String oshiName;
    private String description;
    private String categoryName;
    private CategoryType categoryType;
    private Integer authorId;  // null 허용
    private String authorName;  // null 허용

    @Builder
    public CategoryResponseDto(String imageUrl, CategoryEntity categoryEntity) {
        this.oshiId = categoryEntity.getOshi().getOshiId();
        this.oshiName = categoryEntity.getOshi().getName();
        this.description = categoryEntity.getDescription();
        this.categoryName = categoryEntity.getName();
        this.categoryType = categoryEntity.getType();
        this.imageUrl = imageUrl;

        // Author가 null이 아닌 경우에만 authorId와 authorName 설정
        if (categoryEntity.getAuthor() != null) {
            this.authorId = categoryEntity.getAuthor().getId();
            this.authorName = categoryEntity.getAuthor().getName();
        } else {
            this.authorId = null;  // Author가 없으면 null 설정
            this.authorName = null;  // Author가 없으면 null 설정
        }
    }
}