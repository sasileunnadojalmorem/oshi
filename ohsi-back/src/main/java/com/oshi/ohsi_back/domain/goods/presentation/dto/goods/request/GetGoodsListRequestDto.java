package com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request;

import lombok.Getter;

@Getter
public class GetGoodsListRequestDto {
    private String method;
    private int typeId;
    private String sortOrder;
    private int pagenum;
}
