package com.oshi.ohsi_back.dto.request.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryRequseDto {

    private int oshiid;
    private int pagenum;
    private String sortedBy;
}
