package com.oshi.ohsi_back.dto.request.oshi;

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
