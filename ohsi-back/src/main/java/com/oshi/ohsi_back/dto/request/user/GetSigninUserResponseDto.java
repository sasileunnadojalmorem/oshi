package com.oshi.ohsi_back.dto.request.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;

import lombok.Getter;


@Getter
public class GetSigninUserResponseDto extends ResponseDto {

    private int userId;
    private String email;
    private String username;
    private int profileImage;


    //
    private GetSigninUserResponseDto(UserEntity userEntity) { 

        super (Responsecode.SUCCESSS,Responsemessage.SUCCESSS);
        this.email = userEntity.getEmail();
        this.userId = userEntity.getUserid();
        this.username = userEntity.getUsername();
        this.profileImage = userEntity.getProfileImage();
        
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
