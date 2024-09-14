package com.oshi.ohsi_back.repository.OshiRepository;

import com.oshi.ohsi_back.dto.response.oshi.GetUserOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;

public interface OshiRepositoryCustom {
    // No method body, just the signature
    OshiResponseDto findImageUrlOshiEntity(int oshiId , Integer userId);
    GetUserOshiResponseDto findByUserId(int userId);
}