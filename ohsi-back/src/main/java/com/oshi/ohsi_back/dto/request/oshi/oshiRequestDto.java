package com.oshi.ohsi_back.dto.request.oshi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class oshiRequestDto {
    
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int imageId;
}
