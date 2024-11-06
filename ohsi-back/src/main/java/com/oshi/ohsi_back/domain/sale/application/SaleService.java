package com.oshi.ohsi_back.domain.sale.application;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.domain.sale.presentation.dto.request.*;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.*;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

public interface SaleService {
    ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, UserEntity user);
    GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto);
    GetSaleListResponseDto getSaleList(GetSaleListRequestDto dto);
    void updateSale(UpdateSaleRequestDto dto, UserEntity user);
    void deleteSale(DeleteSaleRequestDto dto, UserEntity user);
}