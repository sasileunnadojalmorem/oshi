package com.oshi.ohsi_back.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.SearchOhsiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.OshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.SearchOshiResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.OshiRepository.OshiRepository;
import com.oshi.ohsi_back.service.Fileservice;
import com.oshi.ohsi_back.service.OshiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OshiServiceImplement implements OshiService {
    
    private final UserRepository userRepository;
    private final OshiRepository oshiRepository;
    private final ImageRepository imageRepository;
    private final Fileservice fileService;

    @Override
    public ResponseEntity<? super OshiResponseDto> postoshi(OshiRequestDto oshiDto, String email) {
        OshiEntity oshiEntity = null;
        ImageEntity imageEntity = null;
        String imageUrl = null;
        MultipartFile file = oshiDto.getFile();  // 파일 객체 가져오기

        try {
            log.info("Checking if user exists with email: {}", email);
            boolean existUser = userRepository.existsByEmail(email);
            oshiEntity = oshiRepository.findByName(oshiDto.getName());
            if (oshiDto.getName() == null || oshiDto.getName().trim().isEmpty()) {
                log.warn("Oshi name is missing or empty");
                throw new CustomException(ErrorCode.AUTHORIZATION_FAIL);  // 새로운 에러 코드 추가
            }

            // 1. 사용자 및 오시 이름 중복 확인
            if (oshiEntity != null) {
                log.warn("Duplicate Oshi name detected: {}", oshiDto.getName());
                throw new CustomException(ErrorCode.DUPLICATE_OSHI);
            }
            if (!existUser) {
                log.warn("User not found with email: {}", email);
                throw new CustomException(ErrorCode.NOT_EXISTED_USER);
            }

            // 2. 오시 엔티티 생성 및 저장 (이미지와 연결)
            log.info("Creating Oshi entity with name: {}", oshiDto.getName());
            oshiEntity = new OshiEntity(oshiDto);  // OshiEntity 생성
            oshiRepository.save(oshiEntity);  // OshiEntity 저장
            log.info("Oshi entity saved with ID: {}", oshiEntity.getOshiId());

            if (file != null && !file.isEmpty()) {
                log.info("Processing image for Oshi with ID: {}", oshiEntity.getOshiId());

                // 2-1. 이미지 엔티티 미리 저장
                imageEntity = new ImageEntity();
                imageEntity.setUrl("temporary-url");  // 임시 URL 설정
                imageEntity.setRelatedId(oshiEntity.getOshiId());  // 오시 ID를 related_id에 설정
                imageEntity.setRelatedType(ImageType.oshi);  // 관련 엔티티 타입을 OSHI로 설정
                imageRepository.save(imageEntity);  // 이미지 엔티티를 먼저 저장
                log.info("Image entity saved with temporary URL for Oshi ID: {}", oshiEntity.getOshiId());

                // 트랜잭션 내에서 파일 저장을 시도
                imageUrl = fileService.SaveImage(file);
                if (imageUrl == null) {
                    log.error("Image saving failed for Oshi ID: {}", oshiEntity.getOshiId());
                    throw new RuntimeException("Image saving failed");  // 파일 저장 실패 시 예외 발생
                }

                // 2-2. 이미지 엔티티의 URL 필드 갱신
                imageEntity.setUrl(imageUrl);
                imageRepository.save(imageEntity);  // URL 업데이트
                log.info("Image entity updated with final URL for Oshi ID: {}", oshiEntity.getOshiId());
            }

            // 3. 성공 시 응답 반환
            OshiResponseDto response = OshiResponseDto.builder()
                    .imageUrl(imageUrl)
                    .oshiEntity(oshiEntity)
                    .build();
            log.info("Oshi registration successful for ID: {}", oshiEntity.getOshiId());

            return ResponseEntity.ok(response);  // 성공 응답

        } catch (CustomException e) {
            log.warn("CustomException during Oshi registration: {}", e.getMessage());
            throw new CustomException(ErrorCode.DATABASE_ERROR); // 예외 처리 (CustomException은 그대로
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
            
            OshiResponseDto oshi = oshiRepository.findImageUrlOshiEntity(dto.getOshiId(),null);
            if (oshi == null) {
                System.out.println("OshiEntity returned null for ID: " + dto.getOshiId() + oshi);
                return GetOshiResponseDto.databaseError();
            }
         
            
            return GetOshiResponseDto.success(oshi);
    
        } catch (Exception e) {
            e.printStackTrace();
            return GetOshiResponseDto.databaseError();
        }
    }

@Override
public ResponseEntity<? super SearchOshiResponseDto> searchOshi(SearchOhsiRequestDto dto) {
    Page<OshiResponseDto> oshiEntities = null;
    Pageable pageable = PageRequest.of(0, 10); // limit 설정

    try {
        // 1. 검색 결과를 가져옴
        oshiEntities = oshiRepository.searchOshiList(dto.getKeyword(), pageable);
        // 2. OshiEntity 리스트를 OshiResponseDto 리스트로 변환
        // 3. 성공 응답 반환
        return SearchOshiResponseDto.success(oshiEntities);        
    } catch (Exception e) {
        e.printStackTrace();
        return SearchOshiResponseDto.databaseError();
    }
}
}