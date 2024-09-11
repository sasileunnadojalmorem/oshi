package com.oshi.ohsi_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.service.SaleService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleContoller {
    
    private final SaleService saleService;

    @PostMapping("add")
    public ResponseEntity<? super AddSaleResponseDto> AddSale(
        @ModelAttribute AddSaleRequestDto requestbody ,
        @AuthenticationPrincipal String email
    )
    {
        ResponseEntity<? super AddSaleResponseDto> responsebody = saleService.addSale(requestbody, email);
        return responsebody;
    }
    @GetMapping("/{saleId}")
    public ResponseEntity<GetSaleInfoResponseDto> getSaleInfo(@PathVariable int saleId) {
        GetSaleInfoRequestDto requestDto = new GetSaleInfoRequestDto(saleId);
        GetSaleInfoResponseDto saleInfoResponseDto = saleService.getSaleInfo(requestDto);

        if (saleInfoResponseDto != null) {
            return new ResponseEntity<>(saleInfoResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Sale not found or an error occurred
        }
    }
    
}
