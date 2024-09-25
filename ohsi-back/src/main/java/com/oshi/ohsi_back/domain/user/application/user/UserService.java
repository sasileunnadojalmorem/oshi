package com.oshi.ohsi_back.domain.user.application.user;


import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetSigninUserResponseDto;

public interface UserService {

    GetSigninUserResponseDto getSignInUser(String email);

    
}
