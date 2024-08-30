package com.oshi.ohsi_back.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;

public interface Fileservice {

    String upload(MultipartFile file);
    ResponseEntity<?super ImageResponseDto> saveImage(ImageRequestDto dto, MultipartFile file);
    Resource getImage(String fileName);

    

    
}
