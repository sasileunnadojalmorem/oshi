package com.oshi.ohsi_back.domain.Auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDto {
    @NotBlank@Email
    private String email;
    @NotBlank
    private String password;
 
    

    
}
