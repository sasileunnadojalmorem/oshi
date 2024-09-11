package com.oshi.ohsi_back.dto.response.Sale;

import com.oshi.ohsi_back.entity.SaleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetSaleInfoResponseDto {
    
    private SaleResponseDto saleResponseDto;

    // 생성자: SaleEntity를 받아서 SaleResponseDto를 생성
    public GetSaleInfoResponseDto(SaleEntity saleEntity) {
        this.saleResponseDto = new SaleResponseDto(saleEntity);  // SaleEntity로부터 SaleResponseDto 생성
    }

    // Static method for creating a response DTO
    public static GetSaleInfoResponseDto createFromSaleEntity(SaleEntity saleEntity) {
        return new GetSaleInfoResponseDto(saleEntity);
    }
}