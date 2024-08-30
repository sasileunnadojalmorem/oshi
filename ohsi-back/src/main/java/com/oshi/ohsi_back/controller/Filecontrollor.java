package com.oshi.ohsi_back.controller;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;
import com.oshi.ohsi_back.service.Fileservice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/file")
@RequiredArgsConstructor
public class Filecontrollor {

    private final Fileservice fileservice;

    @PostMapping("/saveImage")
    public ResponseEntity<? super ImageResponseDto> saveImage(
        @RequestParam("file") MultipartFile file,
        @Valid ImageRequestDto dto) {
        
        // 파일과 DTO를 사용하여 이미지 저장 처리
        ResponseEntity<? super ImageResponseDto> response = fileservice.saveImage(dto, file);
        return response;
    }

    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
        @PathVariable("fileName") String fileName) {
        
        // 파일 이름으로 이미지 리소스를 가져옴
        Resource resource = fileservice.getImage(fileName);
        return resource;
    }
}
