package com.oshi.ohsi_back.service;


import com.oshi.ohsi_back.dto.response.user.GetSigninUserResponseDto;

public interface UserService {

    GetSigninUserResponseDto getSignInUser(String email);

    
}
