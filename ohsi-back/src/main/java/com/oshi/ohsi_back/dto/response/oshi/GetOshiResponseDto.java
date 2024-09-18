package com.oshi.ohsi_back.dto.response.oshi;



import lombok.Builder;
import lombok.Getter;


@Getter

public class GetOshiResponseDto  {

    private OshiResponseDto oshiResponseDto;
    
    @Builder
    public GetOshiResponseDto(OshiResponseDto dto) {
        this.oshiResponseDto = dto;
    }

    
}
