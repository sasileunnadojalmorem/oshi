package com.oshi.ohsi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;

@Repository
public interface BaseGoodsRepository extends JpaRepository<BaseGoodsEntity, Integer> {
}
