package com.oshi.ohsi_back.domain.user.application.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.oshi.ohsi_back.domain.image.infrastructure.ImageRepository;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.domain.user.infrastructure.UserRepository;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetSigninUserResponseDto;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

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