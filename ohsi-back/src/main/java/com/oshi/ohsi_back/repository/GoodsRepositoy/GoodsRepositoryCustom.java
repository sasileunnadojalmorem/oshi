package com.oshi.ohsi_back.repository.GoodsRepositoy;


import org.springframework.data.domain.Pageable;

import com.oshi.ohsi_back.dto.request.goods.GetGoodsListRequestDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsListResponseDto;
import com.oshi.ohsi_back.dto.response.goods.SearchGoodsResponseDto;

public interface GoodsRepositoryCustom {
    SearchGoodsResponseDto searchGoods(String ketword,Pageable pageable);
    GetGoodsListResponseDto findGoods(GetGoodsListRequestDto dto,Pageable pageable);
}
