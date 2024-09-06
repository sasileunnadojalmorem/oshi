package com.oshi.ohsi_back.dto.request.category;

import javax.validation.constraints.NotNull;


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
    private int oshiid;
    @NotNull
    private  String name;
    private  String description;
    @NotNull
    private String type;
    private Integer authorid;
    private Integer imageid;
    
    
}
