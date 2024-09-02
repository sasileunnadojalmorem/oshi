package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;
import com.oshi.ohsi_back.dto.request.oshi.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.dto.response.oshi.AddUserOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.GetUserOshiResponseDto;

public interface UserOshiService {
    ResponseEntity<? super AddUserOshiResponseDto> findstate(AddUserOshiRequsetDto dto, String email);
    ResponseEntity<? super AddUserOshiResponseDto> AddUserOshi(int user_id, int oshi_id);
    ResponseEntity<? super AddUserOshiResponseDto> DelUserOshi(int user_id, int oshi_id);
    ResponseEntity<? super GetUserOshiResponseDto> GetUserOshi(String email);
}
