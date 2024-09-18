package com.oshi.ohsi_back.dto.response.Sale;

import java.util.List;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetSaleListResponseDto {

    private List<SaleResponseDto> saleList;
    private int totalPages;
    private int totalCount;
    private int currentPage;

    @Builder
    public GetSaleListResponseDto(List<SaleResponseDto> saleEntities, int totalPages, int totalCount ,int currentPage) {
        // SaleEntity 리스트를 SaleResponseDto 리스트로 변환
        this.saleList = saleEntities;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
    }
}