package com.oshi.ohsi_back.dto.response.image;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;

import lombok.Getter;

@Getter
public class ImageResponseDto extends ResponseDto{

    private int imageId;
    private ImageResponseDto(ImageEntity imageEntity){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.imageId = imageEntity.getId();  // id

       
    }
    public static ResponseEntity<ImageResponseDto> success(ImageEntity imageEntity) {
        ImageResponseDto responseBody = new ImageResponseDto(imageEntity);
        return ResponseEntity.status(200).body(responseBody);
    }

    
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responsebody = new ResponseDto(Responsecode.NOT_EXISTED_USER,Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);
    }
}
