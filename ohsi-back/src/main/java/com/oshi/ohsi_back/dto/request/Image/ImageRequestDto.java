package com.oshi.ohsi_back.dto.request.Image;

import java.io.File;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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
    private ImageType type;
    
    @NotNull
    private int referenceId;
    
    @NotNull
    private MultipartFile file;

    
}