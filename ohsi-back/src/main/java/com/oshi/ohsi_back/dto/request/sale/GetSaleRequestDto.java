package com.oshi.ohsi_back.dto.request.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSaleRequestDto {

    private String method;
    private int typeId;
    private String sortOrder;
    private int pagenum;

}
