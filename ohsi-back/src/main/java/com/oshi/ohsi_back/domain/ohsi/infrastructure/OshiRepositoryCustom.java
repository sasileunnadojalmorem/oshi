

package com.oshi.ohsi_back.domain.ohsi.infrastructure;

import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetUserOshiResponseDto;

public interface OshiRepositoryCustom {
    // No method body, just the signature
    OshiResponseDto findImageUrlOshiEntity(int oshiId , Integer userId);
    GetUserOshiResponseDto findByUserId(int userId);
}