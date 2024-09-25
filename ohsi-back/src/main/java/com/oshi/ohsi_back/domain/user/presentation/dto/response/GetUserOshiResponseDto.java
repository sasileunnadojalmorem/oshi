

package com.oshi.ohsi_back.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;



@Getter
@NoArgsConstructor
public class GetUserOshiResponseDto {
    private List<OshiResponseDto> oshiList;


    @Builder
    public GetUserOshiResponseDto(List<OshiResponseDto> oshiEntities) {
        this.oshiList = oshiEntities;
    
}
}