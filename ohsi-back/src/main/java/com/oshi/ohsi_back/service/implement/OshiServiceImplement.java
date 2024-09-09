package com.oshi.ohsi_back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.SearchOhsiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.SearchOshiResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.OshiRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.Fileservice;
import com.oshi.ohsi_back.service.OshiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OshiServiceImplement implements OshiService {
    
    private final UserRepository userRepository;
    private final OshiRepository oshiRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileService;

    @Override
@Transactional(rollbackFor = Exception.class)
public ResponseEntity<? super OshiResponseDto> postoshi(oshiRequestDto oshiDto, String email) {
    OshiEntity oshiEntity = null;
    ImageEntity imageEntity = null;
    String imageUrl = null;
    MultipartFile file = oshiDto.getFile();  // 파일 객체 가져오기

    try {
        // 1. 사용자 및 오시 이름 중복 확인
        boolean existUser = userRepository.existsByEmail(email);
        boolean existName = oshiRepository.existsByName(oshiDto.getName());
        if (existName) return OshiResponseDto.duplicationName();
        if (!existUser) return OshiResponseDto.notExistUser();

        // 2. 이미지가 제공되었는지 확인
        if (file != null && !file.isEmpty()) {
            // 2-1. 이미지 엔티티 미리 저장
            imageEntity = new ImageEntity();
            imageEntity.setUrl("temporary-url");  // 임시 URL 설정
            imageRepository.save(imageEntity);  // 이미지 엔티티를 먼저 저장

            // 트랜잭션 내에서 파일 저장을 시도
            imageUrl = fileService.SaveImage(file);
            if (imageUrl == null) {
                throw new RuntimeException("Image saving failed");  // 파일 저장 실패 시 예외 발생
            }

            // 2-2. 이미지 엔티티의 URL 필드 갱신
            imageEntity.setUrl(imageUrl);
            imageRepository.save(imageEntity);  // URL 업데이트
        }

        // 3. 오시 엔티티 생성 및 저장 (이미지와 연결)
        oshiEntity = new OshiEntity(oshiDto, imageEntity);  // ImageEntity가 저장된 후 OshiEntity 생성
        oshiRepository.save(oshiEntity);  // OshiEntity 저장

        // 4. 성공 시 응답 반환
        return OshiResponseDto.success(oshiEntity);

    } catch (Exception e) {
        e.printStackTrace();
        if (imageEntity != null && imageUrl != null) {
            fileService.deleteFile(imageUrl);  // 트랜잭션 실패 시 파일 삭제
        }
        return OshiResponseDto.databaseError();
    }
}
    @Override
    public ResponseEntity<? super GetOshiResponseDto> getoshi(GetOshiRequestDto dto) {
        try {
            System.out.println("Checking if Oshi exists with ID: " + dto.getOshiId());
            boolean existsOshi = oshiRepository.existsByOshiId(dto.getOshiId());
            if (!existsOshi) {
                System.out.println("No Oshi found with ID: " + dto.getOshiId());
                return GetOshiResponseDto.databaseError();
            }
            
            OshiEntity oshiEntity = oshiRepository.findByOshiId(dto.getOshiId());
            if (oshiEntity == null) {
                System.out.println("OshiEntity returned null for ID: " + dto.getOshiId());
                return GetOshiResponseDto.databaseError();
            }
            return GetOshiResponseDto.success(oshiEntity);
    
        } catch (Exception e) {
            e.printStackTrace();
            return GetOshiResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super SearchOshiResponseDto> searchOshi(SearchOhsiRequestDto dto) {
        List<OshiEntity> oshiEntities = null;
        try {
           oshiEntities = oshiRepository.searchOshiList(dto.getKeyword(),10); 
           return SearchOshiResponseDto.success(oshiEntities);        
        } catch (Exception e) {
            e.printStackTrace();
            return SearchOshiResponseDto.databaseError();
        }
    }
}