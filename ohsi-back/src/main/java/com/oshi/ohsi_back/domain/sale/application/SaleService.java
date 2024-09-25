package com.oshi.ohsi_back.domain.sale.application;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.domain.sale.presentation.dto.request.AddSaleRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.DeleteSaleRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.GetSaleListRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.UpdateSaleRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.AddSaleResponseDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.GetSaleListResponseDto;

public interface SaleService {
    ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, String email);
    GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto);
    GetSaleListResponseDto getSaleList(GetSaleListRequestDto dto);
    void updateSale(UpdateSaleRequestDto dto, String email);
    void deleteSale(DeleteSaleRequestDto dto, String email);
}
