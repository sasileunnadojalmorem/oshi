package com.oshi.ohsi_back.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.response.image.ImageDto;
import com.oshi.ohsi_back.dto.response.user.GetSigninUserResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.ImageRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpelement implements UserService  {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    @Override
    public GetSigninUserResponseDto getSignInUser(String email) {
        UserEntity userEntity =  userRepository.findByEmail(email);
        if(userEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        } 
        List<ImageEntity> imageEntity = imageRepository.findByRelatedIdAndRelatedType(userEntity.getUserId(), ImageType.user);
        if(imageEntity != null && !imageEntity.isEmpty()) return new  GetSigninUserResponseDto(userEntity,imageEntity.get(0));
        return GetSigninUserResponseDto.builder().userEntity(userEntity).build();
        
    }
}