package com.oshi.ohsi_back.dto.request.goods;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddGoodsRequestDto {

    @NotNull
    private int oshiId;
    @NotNull
    private int categoryId;
    @NotBlank
    private String name;
    @NotNull
    private int type;
    @Nullable
    private String description;
    
    MultipartFile file;

}
