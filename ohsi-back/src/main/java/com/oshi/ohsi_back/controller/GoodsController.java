package com.oshi.ohsi_back.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.DeleteGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsListRequestDto;
import com.oshi.ohsi_back.dto.request.goods.SearchGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.UpdateGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsListResponseDto;
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
    public AddGoodsResponseDto addGoods(
        @ModelAttribute AddGoodsRequestDto dto,
        @AuthenticationPrincipal String email) 
    {   
        AddGoodsResponseDto response = goodsService.AddGoods(dto, email);
        return response;  // Return the response entity with the map and HTTP status
    }
    @GetMapping("/list")
    public GetGoodsListResponseDto getGoodsList(
        @RequestBody@Valid GetGoodsListRequestDto dto
    )
    {
        GetGoodsListResponseDto response = goodsService.GetGoodsList(dto);
        return response;  // Return the response entity with the map and HTTP status
    }
    @GetMapping("/info")
    public GetGoodsInfoResponseDto getGoodsInfo(
        @RequestBody@Valid GetGoodsInfoRequsetDto dto)
    {
        GetGoodsInfoResponseDto response = goodsService.GetGoodsInfo(dto);
        return response;
    }

    @GetMapping("/search")
    public SearchGoodsResponseDto searchgoods(
        @RequestBody@Valid SearchGoodsRequestDto dto)
    {
        SearchGoodsResponseDto responseEntity = goodsService.Searchgoods(dto);
        return responseEntity;  // Return the response entity with the map and HTTP status

    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateGoods(
        @ModelAttribute @Valid UpdateGoodsRequestDto dto,
        @AuthenticationPrincipal String email
    ){
        goodsService.updateGoods(dto, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGoods(
        @RequestBody @Valid DeleteGoodsRequestDto dto,
        @AuthenticationPrincipal String email
    ){

        goodsService.deleteGoods(dto, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
