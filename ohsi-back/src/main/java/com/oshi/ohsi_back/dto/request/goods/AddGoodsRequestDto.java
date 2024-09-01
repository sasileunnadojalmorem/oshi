package com.oshi.ohsi_back.dto.request.goods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String description;
    private Integer image_id;    
}
