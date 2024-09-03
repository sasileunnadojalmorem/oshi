package com.oshi.ohsi_back.service;

import org.hibernate.type.ImageType;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;

public interface Fileservice {
    ResponseEntity<? super ImageResponseDto> uploadAndSaveImage(MultipartFile file, ImageRequestDto dto, String email);
    Resource getImage(String fileName);
}