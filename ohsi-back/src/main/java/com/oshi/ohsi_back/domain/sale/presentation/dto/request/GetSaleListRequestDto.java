package com.oshi.ohsi_back.domain.sale.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSaleListRequestDto {

    private String method;
    private int typeId;
    private String sortOrder;
    private int pagenum;

}
