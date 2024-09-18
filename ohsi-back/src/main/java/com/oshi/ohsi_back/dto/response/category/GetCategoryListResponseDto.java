package com.oshi.ohsi_back.dto.response.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCategoryListResponseDto  {

    private List<CategoryResponseDto> categoryList;
    private int totalPages;
    private int totalCount;
    private int currentPage;


    @Builder
    public GetCategoryListResponseDto(List<CategoryResponseDto> categoryList, int totalPages, int totalCount,int currentPage) {
        this.categoryList = categoryList;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
    }
    
    

}
