package com.oshi.ohsi_back.dto.request.Image;

import javax.validation.constraints.NotNull;

import com.oshi.ohsi_back.common.ImageType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDto {

    @NotNull
    private String type;
    
    @NotNull
    private int referenceId;

    @NotNull
    private String url;

    
}

