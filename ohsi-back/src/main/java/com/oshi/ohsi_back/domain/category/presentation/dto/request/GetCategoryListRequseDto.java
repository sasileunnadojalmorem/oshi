
package com.oshi.ohsi_back.domain.category.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryListRequseDto {

    @NotNull
    private int oshiid;
    @NotNull
    private int pagenum;
    @NotBlank
    private String sortOrder;
}
