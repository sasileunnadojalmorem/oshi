package com.oshi.ohsi_back.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;

import lombok.Getter;


@Getter
public class GetSigninUserResponseDto extends ResponseDto {

    @NotNull
    private int userId;
    @NotNull@Email
    private String email;
    @NotBlank
    private String username;
    private String profileImageUrl;


    //
    private GetSigninUserResponseDto(UserEntity userEntity) { 

        super (Responsecode.SUCCESSS,Responsemessage.SUCCESSS);
        this.email = userEntity.getEmail();
        this.userId = userEntity.getUserId();
        this.username = userEntity.getUsername();
        this.profileImageUrl = userEntity.getProfileImage().getUrl();
        
    }

    public static ResponseEntity<GetSigninUserResponseDto> success(UserEntity userEntity) {
        GetSigninUserResponseDto result =  new GetSigninUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(Responsecode.NOT_EXISTED_USER,Responsecode.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

    }
    


    
}
