package com.oshi.ohsi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;
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
       
        try {

            boolean existUser = userRepository.existsByEmail(email);
            if(!existUser) return OshiResponseDto.notExistUser();
            OshiEntity oshiEntity = new OshiEntity(dto);
            oshiRepository.save(oshiEntity);
            ImageEntity imageEntity = new ImageEntity(dto,oshiEntity.getOshi_id());
            imageRepository.save(imageEntity);
    

            
        } catch (Exception e) {
            e.printStackTrace();
            return OshiResponseDto.databaseError();
        }
        return OshiResponseDto.success();
        
       
    }   

       


    
    
}

