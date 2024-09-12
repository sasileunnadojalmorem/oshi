package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleListResponseDto;

public interface SaleService {
    ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, String email);
    GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto);
    GetSaleListResponseDto getSaleList(GetSaleListRequestDto dto);
}
