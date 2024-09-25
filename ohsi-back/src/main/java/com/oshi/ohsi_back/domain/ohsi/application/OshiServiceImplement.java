package com.oshi.ohsi_back.domain.ohsi.application;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.image.application.Fileservice;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.oshi.ohsi_back.domain.image.infrastructure.ImageRepository;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;
import com.oshi.ohsi_back.domain.ohsi.infrastructure.OshiRepository;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.GetOshiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.OshiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.SearchOhsiRequestDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.GetOshiResponseDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.SearchOshiResponseDto;
import com.oshi.ohsi_back.domain.user.infrastructure.UserRepository;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

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
    public OshiResponseDto postoshi(OshiRequestDto oshiDto, String email) {
        OshiEntity oshiEntity = null;
        ImageEntity imageEntity = null;
        String imageUrl = null;
        MultipartFile file = oshiDto.getFile();  // 파일 객체 가져오기

        log.info("Checking if user exists with email: {}", email);
        boolean existUser = userRepository.existsByEmail(email);
        oshiEntity = oshiRepository.findByName(oshiDto.getName());
        if (oshiDto.getName() == null || oshiDto.getName().trim().isEmpty()) {
            log.warn("Oshi name is missing or empty");
            throw new CustomException(ErrorCode.AUTHORIZATION_FAIL);  // 새로운 에러 코드 추가
        }

        // 사용자 및 오시 이름 중복 확인
        if (oshiEntity != null) {
            log.warn("Duplicate Oshi name detected: {}", oshiDto.getName());
            throw new CustomException(ErrorCode.DUPLICATE_OSHI);
        }
        if (!existUser) {
            log.warn("User not found with email: {}", email);
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }

        // 오시 엔티티 생성 및 저장 (이미지와 연결)
        log.info("Creating Oshi entity with name: {}", oshiDto.getName());
        oshiEntity = new OshiEntity(oshiDto);  // OshiEntity 생성
        oshiRepository.save(oshiEntity);  // OshiEntity 저장
        log.info("Oshi entity saved with ID: {}", oshiEntity.getOshiId());

        if (file != null && !file.isEmpty()) {
            log.info("Processing image for Oshi with ID: {}", oshiEntity.getOshiId());
            imageEntity = new ImageEntity();
            imageEntity.setUrl("temporary-url");
            imageEntity.setRelatedId(oshiEntity.getOshiId());
            imageEntity.setRelatedType(ImageType.oshi);
            imageRepository.save(imageEntity);

            imageUrl = fileService.SaveImage(file);
            if (imageUrl == null) {
                log.error("Image saving failed for Oshi ID: {}", oshiEntity.getOshiId());
                throw new CustomException(ErrorCode.DATABASE_ERROR, "Image saving failed");
            }

            imageEntity.setUrl(imageUrl);
            imageRepository.save(imageEntity);
            log.info("Image entity updated with final URL for Oshi ID: {}", oshiEntity.getOshiId());
        }

        // 성공 시 DTO 반환
        return OshiResponseDto.builder()
                .imageUrl(imageUrl)
                .oshiEntity(oshiEntity)
                .build();
    }

    @Override
    public GetOshiResponseDto getoshi(GetOshiRequestDto dto) {
        boolean existsOshi = oshiRepository.existsByOshiId(dto.getOshiId());
        if (!existsOshi) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        OshiResponseDto oshi = oshiRepository.findImageUrlOshiEntity(dto.getOshiId(), null);
        if (oshi == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_BOARD);
        }

        // 성공 시 DTO 반환
        return new GetOshiResponseDto(oshi);
    }

    @Override
    public SearchOshiResponseDto searchOshi(SearchOhsiRequestDto dto) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<OshiResponseDto> oshiEntities = oshiRepository.searchOshiList(dto.getKeyword(), pageable);
        
        // 성공 시 DTO 반환
        return new SearchOshiResponseDto(oshiEntities);
    }
}