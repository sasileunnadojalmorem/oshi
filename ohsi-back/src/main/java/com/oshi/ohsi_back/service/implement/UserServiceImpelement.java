package com.oshi.ohsi_back.service.implement;

import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.response.user.GetSigninUserResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpelement implements UserService  {

    private final UserRepository userRepository;

    @Override
    public GetSigninUserResponseDto getSignInUser(String email) {
        UserEntity userEntity =  userRepository.findByEmail(email);
        if(userEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }  

        return new GetSigninUserResponseDto(userEntity);
    }
}