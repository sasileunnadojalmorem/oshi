package com.oshi.ohsi_back.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshi.ohsi_back.dto.response.goodsType.GetGoodsTypeResponseDto;
import com.oshi.ohsi_back.service.GoodsTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goodsType")
@RequiredArgsConstructor
public class GoodsTypeController {

    private final GoodsTypeService goodsTypeService;

    @GetMapping("")
    public ResponseEntity<GetGoodsTypeResponseDto> GetGoodsType(){
        return goodsTypeService.getGoodstypeList();
    }
    
}
