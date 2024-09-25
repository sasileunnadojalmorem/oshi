package com.oshi.ohsi_back.domain.sale.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSaleRequestDto {

    @NotNull
    private int oshiId;
    @NotNull
    private int categoryId;
    @NotNull
    private int goodsId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @Nullable
    MultipartFile file;

}
