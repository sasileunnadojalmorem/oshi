package com.oshi.ohsi_back.service;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.DeleteSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
import com.oshi.ohsi_back.dto.request.sale.UpdateSaleRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleListResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.SaleResponseDto;
import com.oshi.ohsi_back.entity.UserEntity;

public interface SaleService {
    ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, String email);
    GetSaleInfoResponseDto getSaleInfo(GetSaleInfoRequestDto dto);
    GetSaleListResponseDto getSaleList(GetSaleListRequestDto dto);
    SaleResponseDto updateSale(UpdateSaleRequestDto dto, UserEntity user);
    Void deleteSale(DeleteSaleRequestDto dto, UserEntity user);
}
