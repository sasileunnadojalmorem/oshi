package com.oshi.ohsi_back.dto.request.oshi;

import org.springframework.web.multipart.MultipartFile;

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
    private String description;
    private MultipartFile file;
}
