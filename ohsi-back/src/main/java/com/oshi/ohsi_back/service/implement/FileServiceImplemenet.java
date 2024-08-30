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

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String saveFilePath = filePath + saveFileName;

        try {
            file.transferTo(new File(saveFilePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;    
        }

        return fileUrl + saveFileName;
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

    @Override
    public ResponseEntity<? super ImageResponseDto> saveImage(ImageRequestDto dto, MultipartFile file) {
        String uploadedFileUrl = upload(file); // 로컬 변수 이름을 변경
        if (uploadedFileUrl == null) {
            return ImageResponseDto.databaseError(); // 업로드 오류 처리
        }

        try {
            ImageEntity imageEntity = new ImageEntity(dto);
            imageEntity.setUrl(uploadedFileUrl);  // 업로드된 파일 URL을 엔티티에 설정
            imageRepository.save(imageEntity);
            return ImageResponseDto.success(); // 성공적으로 업로드된 파일 URL 반환
        } catch (Exception e) {
            e.printStackTrace();
            return ImageResponseDto.databaseError();
        }
    }
}
