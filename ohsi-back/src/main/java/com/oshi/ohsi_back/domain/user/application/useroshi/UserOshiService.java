package com.oshi.ohsi_back.domain.user.application.useroshi;

import com.oshi.ohsi_back.domain.user.presentation.dto.request.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.AddUserOshiResponseDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetUserOshiResponseDto;

public interface UserOshiService {
    AddUserOshiResponseDto findstate(AddUserOshiRequsetDto dto, String email); // 변경된 반환 타입
    GetUserOshiResponseDto GetUserOshi(String email);
}