package com.oshi.ohsi_back.domain.Auth.application;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignInRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.request.SignUpRequestDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SignUpResponseDto;
import com.oshi.ohsi_back.domain.Auth.presentation.dto.response.SigninResponseDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.domain.user.infrastructure.UserRepository;
import com.oshi.ohsi_back.domain.user.jwt.provider.jwtProvider;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtProvider jwtProvider;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto dto,HttpServletRequest http) {
        
            String email = dto.getEmail();
            boolean isEmailExist = userRepository.existsByEmail(email);
            if (isEmailExist) { 
                throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
            }

            String name = dto.getUsername();
            boolean isNameExist = userRepository.existsByUsername(name);
            if (isNameExist) {
                throw new CustomException(ErrorCode.DUPLICATE_NAME);
            }

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity); // UserEntity 객체를 저장
            String token = jwtProvider.create(email);

        

        return SignUpResponseDto.builder().token(token).build();
    }

    @Override
    public SigninResponseDto signIn(SignInRequestDto dto) {

        String token = null;
        

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if( userEntity == null ) throw new CustomException(ErrorCode.SIGN_IN_FAILED);

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatch = passwordEncoder.matches(password, encodedPassword);
            if(!isMatch) throw new CustomException(ErrorCode.SIGN_IN_FAILED);

            
            token = jwtProvider.create(email);


       

        return SigninResponseDto.builder()
                    .token(token).build();
    }
}
