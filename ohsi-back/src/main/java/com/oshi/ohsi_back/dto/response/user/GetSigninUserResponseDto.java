package com.oshi.ohsi_back.dto.response.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;


@Getter
public class GetSigninUserResponseDto  {

    @NotNull
    private int userId;
    @NotNull@Email
    private String email;
    @NotBlank
    private String username;
    private String profileImageUrl;


    //
    @Builder
    public GetSigninUserResponseDto(UserEntity userEntity,ImageEntity imageEntity) { 

        this.email = userEntity.getEmail();
        this.userId = userEntity.getUserId();
        this.username = userEntity.getUsername();
        if(imageEntity != null) this.profileImageUrl = imageEntity.getUrl();
        
    }


    


    
}
