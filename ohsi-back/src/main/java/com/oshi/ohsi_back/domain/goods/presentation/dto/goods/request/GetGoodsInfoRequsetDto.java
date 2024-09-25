package com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGoodsInfoRequsetDto {
    @NotNull
    private int goodsId;
}
