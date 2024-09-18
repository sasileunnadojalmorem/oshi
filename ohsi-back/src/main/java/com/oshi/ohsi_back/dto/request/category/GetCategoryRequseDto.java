package com.oshi.ohsi_back.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryRequseDto {

    @NotNull
    private int oshiid;
    @NotNull
    private int pagenum;
    @NotBlank
    private String sortedBy;
}
