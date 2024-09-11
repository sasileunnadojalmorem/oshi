package com.oshi.ohsi_back.dto.response.Sale;

import com.oshi.ohsi_back.entity.SaleEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class GetSaleResponseDto  {
    
    private SaleResponseDto saleResponseDto;

    // 생성자: SaleEntity를 받아서 SaleResponseDto를 생성
    public GetSaleResponseDto(SaleEntity saleEntity) {
        this.saleResponseDto = new SaleResponseDto(saleEntity);  // SaleEntity로부터 SaleResponseDto 생성
    }

    public static GetSaleResponseDto getSaleResponseDto(SaleEntity saleEntity) {
        GetSaleResponseDto responsebody = new GetSaleResponseDto(saleEntity);
        return responsebody;
    }
}