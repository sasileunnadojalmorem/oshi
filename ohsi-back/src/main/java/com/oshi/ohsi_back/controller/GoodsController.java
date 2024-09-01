package com.oshi.ohsi_back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @PostMapping("/add")
    public ResponseEntity<? super AddGoodsResponseDto > addOshi(
        @RequestBody@Valid AddGoodsRequestDto dto, @AuthenticationPrincipal String email) 
    {   
        ResponseEntity<?super AddGoodsResponseDto> response = goodsService.AddOshi(dto, email);
        return response;  // Return the response entity with the map and HTTP status
    }
}
