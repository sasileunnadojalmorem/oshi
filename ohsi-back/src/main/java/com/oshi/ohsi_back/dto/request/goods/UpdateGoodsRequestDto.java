package com.oshi.ohsi_back.dto.request.goods;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.lang.Nullable;
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
public class UpdateGoodsRequestDto {
    
    @NotNull
    private int goodsId;
    @NotBlank
    private String name;
    @Nullable
    private String description;
    @NotNull
    private int type;

    List<MultipartFile> file;
    
}
