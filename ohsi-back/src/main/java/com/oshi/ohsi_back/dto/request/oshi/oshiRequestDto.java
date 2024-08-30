package com.oshi.ohsi_back.dto.request.oshi;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String profileImageUrl;
}
