package com.oshi.ohsi_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleInfoRequestDto;
import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleInfoResponseDto;
import com.oshi.ohsi_back.dto.response.Sale.GetSaleListResponseDto;
import com.oshi.ohsi_back.service.SaleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

        return new ResponseEntity<>(saleInfoResponseDto, HttpStatus.OK);
       
    }

    @GetMapping("/list")
    public ResponseEntity<GetSaleListResponseDto> getSaleList(
        @RequestBody@Valid GetSaleListRequestDto dto
    ){
        GetSaleListResponseDto responseDto = saleService.getSaleList(dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    
}
