package com.oshi.ohsi_back.dto.request.sale;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import java.math.BigDecimal;
@Getter
public class AddSaleRequestDto {
    @NotNull
    private int user_id;
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
    private int image_id;
}
