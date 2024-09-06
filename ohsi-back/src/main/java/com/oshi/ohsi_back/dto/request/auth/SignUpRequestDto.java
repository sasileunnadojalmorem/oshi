package com.oshi.ohsi_back.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto  {
    
    
    @NotBlank 
    @Email    
    private String email;
    
    @NotBlank 
    @Size(max = 20, min = 8)
    private String password;
    
    @NotBlank
    private String username;

    private Integer profileImageId;

   

    
}
