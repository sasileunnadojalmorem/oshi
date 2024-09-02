package com.oshi.ohsi_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.service.SaleService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleContoller {
    
    private final SaleService saleService;

    @PostMapping("add")
    public ResponseEntity<? super AddSaleResponseDto> AddSale(
        @RequestBody@Valid AddSaleRequestDto requestbody ,
        @AuthenticationPrincipal String email
    )
    {
        ResponseEntity<? super AddSaleResponseDto> responsebody = saleService.addSale(requestbody, email);
        return responsebody;
    }
    
    
}
