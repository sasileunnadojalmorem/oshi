package com.oshi.ohsi_back.dto.request.sale;

import jakarta.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import java.math.BigDecimal;
@Getter
public class AddSaleRequestDto {

    @NotNull
    private int oshi_id;
    @NotNull
    private int category_id;
    @NotNull
    private int goods_id;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @Nullable
    private Integer image_id;
}
