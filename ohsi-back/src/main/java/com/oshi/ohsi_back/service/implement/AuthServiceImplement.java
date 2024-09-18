package com.oshi.ohsi_back.service.implement;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.auth.SignInRequestDto;
import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;
import com.oshi.ohsi_back.dto.response.auth.SignUpResponseDto;
import com.oshi.ohsi_back.dto.response.auth.SigninResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.provider.jwtProvider;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.AuthService;

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
