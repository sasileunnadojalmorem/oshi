package com.oshi.ohsi_back.service;

import com.oshi.ohsi_back.dto.request.oshi.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.dto.response.oshi.AddUserOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.GetUserOshiResponseDto;

public interface UserOshiService {
    AddUserOshiResponseDto findstate(AddUserOshiRequsetDto dto, String email); // 변경된 반환 타입
    GetUserOshiResponseDto GetUserOshi(String email);
}