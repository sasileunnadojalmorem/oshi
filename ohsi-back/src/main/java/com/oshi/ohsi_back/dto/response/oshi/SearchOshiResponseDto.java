package com.oshi.ohsi_back.dto.response.oshi;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.OshiEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOshiResponseDto extends ResponseDto {
    private Page<OshiResponseDto> oshiEntityList;
    
    private SearchOshiResponseDto(Page<OshiResponseDto> oshiEntityList){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.oshiEntityList = oshiEntityList;
    }
    public static ResponseEntity<? super SearchOshiResponseDto> success(Page<OshiResponseDto> oshiEntityList){
        SearchOshiResponseDto responseDto = new SearchOshiResponseDto(oshiEntityList);
        return ResponseEntity.status(200).body(responseDto);
    }
}
