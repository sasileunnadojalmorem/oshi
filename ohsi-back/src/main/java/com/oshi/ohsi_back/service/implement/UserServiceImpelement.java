package com.oshi.ohsi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.user.GetSigninUserResponseDto;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpelement implements UserService  {


    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSigninUserResponseDto> getSignInUser(String email) {
        UserEntity userEntity =  null;
        try {

            userEntity = userRepository.findByEmail(email);
            if(userEntity == null)  return GetSigninUserResponseDto.notExistUser();
            
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseDto.databaseError();

        }

        return GetSigninUserResponseDto.success(userEntity);
        
    }

    
}
