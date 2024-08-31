package com.oshi.ohsi_back.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;
import com.oshi.ohsi_back.dto.response.image.UrlResponseDto;

public interface Fileservice {

    ResponseEntity<? super UrlResponseDto > upload(MultipartFile file,String email);
    ResponseEntity<? super ImageResponseDto> saveImage(ImageRequestDto dto, String email);
    Resource getImage(String fileName);

    

    
}
