package com.oshi.ohsi_back.dto.request.sale;

import java.math.BigDecimal;

import com.oshi.ohsi_back.enums.SaleStatusEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSaleRequestDto {
    @NotNull
    private int saleId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private SaleStatusEnum state;
}
