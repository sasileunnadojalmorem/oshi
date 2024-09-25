

package com.oshi.ohsi_back.domain.goods.infrastructue.GoodsRepositoy;


import org.springframework.data.domain.Pageable;

import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.request.GetGoodsListRequestDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.GetGoodsListResponseDto;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goods.response.SearchGoodsResponseDto;

public interface GoodsRepositoryCustom {
    SearchGoodsResponseDto searchGoods(String ketword,Pageable pageable);
    GetGoodsListResponseDto findGoods(GetGoodsListRequestDto dto,Pageable pageable);
}
