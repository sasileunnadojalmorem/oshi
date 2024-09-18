package com.oshi.ohsi_back.dto.response.category;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchCategoryResponseDto {

    private List<CategoryResponseDto> categoryEntities;
    

    @Builder
    public SearchCategoryResponseDto(Page<CategoryResponseDto> category) {
        this.categoryEntities = category.getContent();          // 카테고리 엔티티 리스트
                    // 현재 페이지 번호
    }
}