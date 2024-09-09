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
public class AddCategoryRequsetDto {

    @NotNull
    private int oshiId;
    @NotBlank
    private  String name;

    private  String description;
    
        @NotBlank
    private String type;

    private MultipartFile file;

    private Integer authorid;

    private Integer imageid;
    
    
}
