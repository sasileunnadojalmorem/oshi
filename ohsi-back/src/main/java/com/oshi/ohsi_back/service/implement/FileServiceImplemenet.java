package com.oshi.ohsi_back.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.Fileservice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImplemenet implements Fileservice {

    @Value("${file.path}")  
    private String filePath;
    
    @Value("${file.url}")  
    private String fileUrl;
    
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super ImageResponseDto> uploadAndSaveImage(MultipartFile file, ImageRequestDto dto, String email) {
        // 사용자 존재 여부 확인
        boolean existUser = userRepository.existsByEmail(email);
        if (!existUser) return ImageResponseDto.databaseError();  // 또는 다른 적절한 응답

        // 파일이 비어있는지 확인
        if (file.isEmpty()) return ImageResponseDto.databaseError();  // 또는 다른 적절한 응답

        // 파일 이름 생성
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String saveFilePath = filePath + saveFileName;

        try {
            // 파일 저장
            file.transferTo(new File(saveFilePath));
            
            // 이미지 URL 생성
            String imageUrl = fileUrl + saveFileName;

            // 이미지 엔티티 생성 및 저장
            ImageEntity imageEntity = new ImageEntity(dto, imageUrl);
            imageRepository.save(imageEntity);

            return ImageResponseDto.success(imageEntity);  // 성공적으로 저장된 이미지 ID 반환
        } catch (Exception e) {
            e.printStackTrace();
            return ImageResponseDto.databaseError();
        }
    }

    @Override
    public Resource getImage(String fileName) {
        try {
            return new UrlResource("file:" + filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}