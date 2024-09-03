package com.oshi.ohsi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.oshi.GetOshiRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.GetOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.OshiRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.OshiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OshiServiceImplement implements OshiService{
    
    private final UserRepository userRepository;
    private final OshiRepository oshiRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super OshiResponseDto> postoshi(oshiRequestDto dto, String email) {
       OshiEntity oshiEntity = null;
        try {

            boolean existUser = userRepository.existsByEmail(email);
            boolean existName = oshiRepository.existsByName(dto.getName());
            if(existName) return OshiResponseDto.duplicationName();
            if(!existUser) return OshiResponseDto.notExistUser();
            boolean existsImage = imageRepository.existsById(dto.getImageId());
            if(!existsImage) return OshiResponseDto.databaseError();
            oshiEntity = new OshiEntity(dto);
            oshiRepository.save(oshiEntity);
            return OshiResponseDto.success(oshiEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
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
}

