package com.oshi.ohsi_back.domain.user.application.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.oshi.ohsi_back.domain.image.infrastructure.ImageRepository;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.domain.user.execption.UserException;
import com.oshi.ohsi_back.domain.user.infrastructure.UserRepository;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetSigninUserResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpelement implements UserService  {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public GetSigninUserResponseDto getUserInformation(UserEntity user) {
        // UserEntity가 null일 경우에 대한 예외 처리
        if (user == null) {
            throw new UserException(ErrorCode.NOT_EXISTED_USER);
        }
        return GetSigninUserResponseDto.builder()
                .userEntity(user)
                .build();
    }
}