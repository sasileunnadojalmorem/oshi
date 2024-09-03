package com.oshi.ohsi_back.dto.request.goods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
@Getter
public class AddGoodsRequestDto {

    @NotNull
    private int oshi_id;
    @NotNull
    private int category_id;
    @NotBlank
    private String name;
    @NotNull
    private int type;
    @Nullable
    private String description;
    @Nullable
    private Integer image_id;    
}
