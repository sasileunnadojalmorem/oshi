package com.oshi.ohsi_back.dto.response.oshi;

import java.util.List;

import org.springframework.data.domain.Page;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOshiResponseDto {
    private List<OshiResponseDto> oshiEntityList;
    
    public SearchOshiResponseDto(Page<OshiResponseDto> oshiEntityList){
        this.oshiEntityList = oshiEntityList.getContent();
    }
    
}
