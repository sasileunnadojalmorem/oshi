package com.oshi.ohsi_back.dto.request.auth;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    

    
}
