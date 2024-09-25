package com.oshi.ohsi_back.domain.sale.presentation.dto.request;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.domain.sale.domain.enums.SaleStatusEnum;

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
    
    private MultipartFile file;

}
