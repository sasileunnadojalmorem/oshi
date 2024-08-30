package com.oshi.ohsi_back.dto.request.Image;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequsetDto {
    @NotBlank
    private String id;
    @NotBlank 
    private String url;
    @NotBlank
    private String type;
    @NotBlank
    private String referenceId;
    
}
