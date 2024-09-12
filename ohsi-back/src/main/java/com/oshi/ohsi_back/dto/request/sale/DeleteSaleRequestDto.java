package com.oshi.ohsi_back.dto.request.sale;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSaleRequestDto {
    @NotNull
    private int saleId;
}
