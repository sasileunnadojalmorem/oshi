package com.oshi.ohsi_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.DeleteSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
import com.oshi.ohsi_back.dto.request.sale.UpdateSaleRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleListResponseDto;
import com.oshi.ohsi_back.service.SaleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    // 판매 정보를 추가하는 API
    @PostMapping("add")
    public ResponseEntity<? super AddSaleResponseDto> addSale(
        @ModelAttribute AddSaleRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super AddSaleResponseDto> response = saleService.addSale(requestBody, email);
        return response;
    }

    // 특정 판매 정보를 조회하는 API
    @GetMapping("/{saleId}")
    public ResponseEntity<GetSaleInfoResponseDto> getSaleInfo(@PathVariable int saleId) {
        GetSaleInfoRequestDto requestDto = new GetSaleInfoRequestDto(saleId);
        GetSaleInfoResponseDto saleInfoResponseDto = saleService.getSaleInfo(requestDto);
        return new ResponseEntity<>(saleInfoResponseDto, HttpStatus.OK);
    }

    // 판매 목록을 조회하는 API
    @GetMapping("/list")
    public ResponseEntity<GetSaleListResponseDto> getSaleList(
        @RequestBody @Valid GetSaleListRequestDto dto
    ) {
        GetSaleListResponseDto responseDto = saleService.getSaleList(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 판매 정보를 업데이트하는 API
    @PutMapping("update")
    public ResponseEntity<Void> updateSale(
        @ModelAttribute @Valid UpdateSaleRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {
        saleService.updateSale(requestBody, email);  // UserEntity 생성자 사용
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 판매 정보를 삭제하는 API
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteSale(
        @RequestBody @Valid DeleteSaleRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {

        saleService.deleteSale(requestBody, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}