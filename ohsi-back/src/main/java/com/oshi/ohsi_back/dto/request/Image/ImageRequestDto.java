package com.oshi.ohsi_back.dto.request.Image;

import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDto {

   
    
    @NotNull(message = "file is required")
    private MultipartFile file;

    private String url;

    
}