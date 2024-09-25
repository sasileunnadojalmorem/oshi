

package com.oshi.ohsi_back.domain.category.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCategoryRequestDto {
    @NotNull
    private String keyword;

    
}
