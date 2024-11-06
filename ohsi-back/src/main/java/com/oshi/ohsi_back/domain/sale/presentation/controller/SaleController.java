package com.oshi.ohsi_back.domain.sale.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.domain.sale.application.SaleService;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.*;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.*;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("add")
    public ResponseEntity<? super AddSaleResponseDto> addSale(
        @ModelAttribute AddSaleRequestDto requestBody,
        @AuthenticationPrincipal UserEntity user
    ) {
        ResponseEntity<? super AddSaleResponseDto> response = saleService.addSale(requestBody, user);
        return response;
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<GetSaleInfoResponseDto> getSaleInfo(@PathVariable int saleId) {
        GetSaleInfoRequestDto requestDto = new GetSaleInfoRequestDto(saleId);
        GetSaleInfoResponseDto saleInfoResponseDto = saleService.getSaleInfo(requestDto);
        return new ResponseEntity<>(saleInfoResponseDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GetSaleListResponseDto> getSaleList(
        @RequestBody @Valid GetSaleListRequestDto dto
    ) {
        GetSaleListResponseDto responseDto = saleService.getSaleList(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateSale(
        @ModelAttribute @Valid UpdateSaleRequestDto requestBody,
        @AuthenticationPrincipal UserEntity user
    ) {
        saleService.updateSale(requestBody, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteSale(
        @RequestBody @Valid DeleteSaleRequestDto requestBody,
        @AuthenticationPrincipal UserEntity user
    ) {
        saleService.deleteSale(requestBody, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}