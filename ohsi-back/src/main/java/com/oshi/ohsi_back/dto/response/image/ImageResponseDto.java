package com.oshi.ohsi_back.dto.response.image;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;

import lombok.Getter;

@Getter
public class ImageResponseDto extends ResponseDto{
    private ImageResponseDto(ImageEntity imageEntity){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
       
    }
    public static ResponseEntity<ImageResponseDto> success(ImageEntity imageEntity) {
        ImageResponseDto responseBody = new ImageResponseDto(imageEntity);
        return ResponseEntity.status(200).body(responseBody);
    }
}
