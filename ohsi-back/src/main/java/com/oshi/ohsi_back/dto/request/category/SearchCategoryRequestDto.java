package com.oshi.ohsi_back.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCategoryRequestDto {
    @NotNull
    private String keyword;

    
}
