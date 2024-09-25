package com.oshi.ohsi_back.domain.user.presentation.dto.response;



import lombok.Builder;
import lombok.Getter;

@Getter
public class AddUserOshiResponseDto  {

    private int userOshiState;

    // 기본 생성자
    @Builder
    public AddUserOshiResponseDto(int userOshiState){
        this.userOshiState = userOshiState;
    }
    
}
