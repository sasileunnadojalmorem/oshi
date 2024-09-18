package com.oshi.ohsi_back.dto.request.goods;

import lombok.Getter;

@Getter
public class GetGoodsListRequestDto {
    private String method;
    private int typeId;
    private String sortOrder;
    private int pagenum;
}
