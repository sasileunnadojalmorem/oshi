package com.oshi.ohsi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.dto.response.goodsType.GoodsTypeDto;
import com.oshi.ohsi_back.entity.GoodsTypeEntity;

import java.util.List;
@Repository
public interface GoodsTypeRepository extends JpaRepository<GoodsTypeEntity, Integer> {
    
    @Query("SELECT new com.oshi.ohsi_back.dto.response.goodsType.GoodsTypeDto(g)" + //
                " FROM GoodsTypeEntity g")
    List<GoodsTypeDto> ShowGoods();
}
