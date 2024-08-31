package com.oshi.ohsi_back.dto.response.image;


import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class UrlResponseDto extends ResponseDto {

    private String url;
    private UrlResponseDto(String url) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        
        this.url = url;
    }

    public static ResponseEntity<UrlResponseDto> success(String url) {
        UrlResponseDto response = new UrlResponseDto(url);
        return ResponseEntity.status(200).body(response);   
    }

}
