package com.oshi.ohsi_back.dto.request.goods;

import lombok.Getter;

@Getter
public class GetGoodsRequestDto {
    private String method;
    private int id;
    private String sortOrder;
    private int pagenum;
}
