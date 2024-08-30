package com.oshi.ohsi_back.dto.response.image;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class ImageResponseDto extends ResponseDto{
    private ImageResponseDto(){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
       
    }
    public static ResponseEntity<ImageResponseDto> success() {
        ImageResponseDto responseBody = new ImageResponseDto();
        return ResponseEntity.status(200).body(responseBody);
    }
}
