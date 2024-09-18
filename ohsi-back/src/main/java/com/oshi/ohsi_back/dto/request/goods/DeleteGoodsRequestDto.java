package com.oshi.ohsi_back.dto.request.goods;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteGoodsRequestDto {

    @NotNull
    private int goodsId;
}
