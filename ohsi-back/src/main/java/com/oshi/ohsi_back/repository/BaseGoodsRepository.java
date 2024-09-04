package com.oshi.ohsi_back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.entity.BaseGoodsEntity;

@Repository
public interface BaseGoodsRepository extends JpaRepository<BaseGoodsEntity, Integer> {
    boolean existsByName(String goodsName);
    boolean existsByGoodsId(int goodsId);
    BaseGoodsEntity findByGoodsId(int goodsId);
    Page<BaseGoodsEntity> findByCategoryId(int categoryId, Pageable pageable);
    Page<BaseGoodsEntity> findByOshiId(int oshiId,Pageable pageable);
    @Query(value = "SELECT * FROM base_goods o WHERE o.name LIKE CONCAT('%', :keyword, '%') LIMIT :limit", nativeQuery = true)
    List<BaseGoodsEntity> searchGoodsList(@Param("keyword") String keyword, @Param("limit") int limit);
}
