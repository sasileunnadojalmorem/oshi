package com.oshi.ohsi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.GoodsTypeEntity;

@Repository
public interface GoodsTypeRepository extends JpaRepository<GoodsTypeEntity, Integer> {
}
