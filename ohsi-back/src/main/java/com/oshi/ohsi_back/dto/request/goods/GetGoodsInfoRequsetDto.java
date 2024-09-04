package com.oshi.ohsi_back.dto.request.goods;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGoodsInfoRequsetDto {
    @NotNull
    private int goodsId;
}
