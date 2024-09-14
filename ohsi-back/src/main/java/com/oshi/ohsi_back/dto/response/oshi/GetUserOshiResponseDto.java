package com.oshi.ohsi_back.dto.response.oshi;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;



@Getter
@NoArgsConstructor
public class GetUserOshiResponseDto {
    private List<OshiResponseDto> oshiList;


    @Builder
    public GetUserOshiResponseDto(List<OshiResponseDto> oshiEntities) {
        this.oshiList = oshiEntities;
    
}
}