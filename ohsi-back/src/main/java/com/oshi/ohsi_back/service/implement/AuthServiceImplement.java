package com.oshi.ohsi_back.service.implement;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.auth.SignInRequestDto;
import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.dto.response.auth.SignUpResponseDto;
import com.oshi.ohsi_back.dto.response.auth.SigninResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;
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
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto,HttpServletRequest http) {
        try {
            String email = dto.getEmail();
            boolean isEmailExist = userRepository.existsByEmail(email);
            if (isEmailExist) { 
                return SignUpResponseDto.duplicationEmail();
            }

            String name = dto.getUsername();
            boolean isNameExist = userRepository.existsByUsername(name);
            if (isNameExist) {
                return SignUpResponseDto.duplicationName();
            }

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity); // UserEntity 객체를 저장

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SigninResponseDto> signIn(SignInRequestDto dto) {

        String token = null;
        try {   

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if( userEntity == null ) return SigninResponseDto.SIGN_IN_FAILED();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatch = passwordEncoder.matches(password, encodedPassword);
            if( !isMatch) return SigninResponseDto.SIGN_IN_FAILED();

            
            token = jwtProvider.create(email);


        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseDto.databaseError();

        }

        return SigninResponseDto.success(token);
    }
}
