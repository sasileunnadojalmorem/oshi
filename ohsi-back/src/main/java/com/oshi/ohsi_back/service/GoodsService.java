package com.oshi.ohsi_back.service;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.DeleteGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsListRequestDto;
import com.oshi.ohsi_back.dto.request.goods.SearchGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.UpdateGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsListResponseDto;
import com.oshi.ohsi_back.dto.response.goods.SearchGoodsResponseDto;

public interface GoodsService {

    AddGoodsResponseDto AddGoods(AddGoodsRequestDto dto, String email);
    GetGoodsInfoResponseDto GetGoodsInfo(GetGoodsInfoRequsetDto dto);
    SearchGoodsResponseDto Searchgoods(SearchGoodsRequestDto dto);
    GetGoodsListResponseDto GetGoodsList(GetGoodsListRequestDto dto);
    void updateGoods(UpdateGoodsRequestDto dto, String email);
    void deleteGoods(DeleteGoodsRequestDto dto, String email);
}