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
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

public interface GoodsService {
    AddGoodsResponseDto addGoods(AddGoodsRequestDto dto, UserEntity user);
    GetGoodsInfoResponseDto getGoodsInfo(GetGoodsInfoRequsetDto dto);
    SearchGoodsResponseDto searchGoods(SearchGoodsRequestDto dto);
    GetGoodsListResponseDto getGoodsList(GetGoodsListRequestDto dto);
    void updateGoods(UpdateGoodsRequestDto dto, UserEntity user);
    void deleteGoods(DeleteGoodsRequestDto dto, UserEntity user);
}