package com.oshi.ohsi_back.dto.request.oshi;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOshiRequestDto {
    @NotNull
    private int oshiId; // 변수명을 카멜 케이스로 수정
}