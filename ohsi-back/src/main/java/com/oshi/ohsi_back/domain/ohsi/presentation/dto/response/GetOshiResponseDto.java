package com.oshi.ohsi_back.domain.ohsi.presentation.dto.response;



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
