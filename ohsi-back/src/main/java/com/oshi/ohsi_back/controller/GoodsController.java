package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.SearchGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.SearchGoodsResponseDto;
import com.oshi.ohsi_back.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @PostMapping("/add")
    public ResponseEntity<? super AddGoodsResponseDto > addGoods(
        @RequestBody@Valid AddGoodsRequestDto dto, @AuthenticationPrincipal String email) 
    {   
        ResponseEntity<?super AddGoodsResponseDto> response = goodsService.AddGoods(dto, email);
        return response;  // Return the response entity with the map and HTTP status
    }
    @GetMapping("/")
    public ResponseEntity<? super GetGoodsResponseDto> getGoods(
        @RequestBody@Valid GetGoodsRequestDto dto
    )
    {
        ResponseEntity<? super GetGoodsResponseDto> response = goodsService.GetGoods(dto);
        return response;  // Return the response entity with the map and HTTP status
    }
    @GetMapping("/info")
    public ResponseEntity<? super GetGoodsInfoResponseDto> getGoodsInfo(
        @RequestBody@Valid GetGoodsInfoRequsetDto dto)
    {
        ResponseEntity<? super GetGoodsInfoResponseDto> response = goodsService.GetGoodsInfo(dto);
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<? super SearchGoodsResponseDto> searchgoods(
        @RequestBody@Valid SearchGoodsRequestDto dto)
    {
        ResponseEntity<? super SearchGoodsResponseDto> responseEntity = goodsService.Searchgoods(dto);
        return responseEntity;  // Return the response entity with the map and HTTP status

    }
}
