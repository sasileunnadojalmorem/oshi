package com.oshi.ohsi_back.dto.request.category;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequestDto {

    @NotNull(message = "Oshi ID는 필수입니다.")
    private Integer oshiId;

    @NotBlank(message = "카테고리 이름은 필수입니다.")
    private String name;

    private String description;
    
    @NotBlank(message = "카테고리 타입은 필수입니다.")
    private String type;

    private MultipartFile file;

    private Integer authorId;
}