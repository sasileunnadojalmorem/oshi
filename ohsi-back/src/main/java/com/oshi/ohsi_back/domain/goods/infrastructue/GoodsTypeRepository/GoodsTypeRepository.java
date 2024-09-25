package com.oshi.ohsi_back.domain.goods.infrastructue.GoodsTypeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.goods.domain.entity.GoodsTypeEntity;
import com.oshi.ohsi_back.domain.goods.presentation.dto.goodsType.response.GoodsTypeDto;

import java.util.List;

@Repository
public interface GoodsTypeRepository extends JpaRepository<GoodsTypeEntity, Integer> {
    
    @Query("SELECT new com.oshi.ohsi_back.domain.goods.presentation.dto.goodsType.response.GoodsTypeDto(g)" + 
           " FROM GoodsTypeEntity g")
    List<GoodsTypeDto> ShowGoods();
}
