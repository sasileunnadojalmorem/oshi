package com.oshi.ohsi_back.domain.ohsi.presentation.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OshiRequestDto {
    
    @NotBlank
    private String name;
    private String description;
    private MultipartFile file;
}
