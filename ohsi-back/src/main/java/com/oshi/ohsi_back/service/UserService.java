package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.user.GetSigninUserResponseDto;

public interface UserService {

    ResponseEntity<? super GetSigninUserResponseDto> getSignInUser(String email);

    
}
