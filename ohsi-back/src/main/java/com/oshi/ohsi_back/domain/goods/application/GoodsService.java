package com.oshi.ohsi_back.domain.goods.application;

import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.AddGoodsRequestDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.DeleteGoodsRequestDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.GetGoodsListRequestDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.SearchGoodsRequestDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.UpdateGoodsRequestDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.AddGoodsResponseDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.GetGoodsListResponseDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.SearchGoodsResponseDto;

public interface GoodsService {

    AddGoodsResponseDto AddGoods(AddGoodsRequestDto dto, String email);
    GetGoodsInfoResponseDto GetGoodsInfo(GetGoodsInfoRequsetDto dto);
    SearchGoodsResponseDto Searchgoods(SearchGoodsRequestDto dto);
    GetGoodsListResponseDto GetGoodsList(GetGoodsListRequestDto dto);
    void updateGoods(UpdateGoodsRequestDto dto, String email);
    void deleteGoods(DeleteGoodsRequestDto dto, String email);
}