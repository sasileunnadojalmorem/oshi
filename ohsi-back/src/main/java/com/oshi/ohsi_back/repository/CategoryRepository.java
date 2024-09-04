package com.oshi.ohsi_back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.entity.OshiEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    boolean existsByName(String name);
    CategoryEntity findByCategoryId(int categoryId);
    Page<CategoryEntity> findByOshiId(int oshiId, Pageable pageable);
    @Query(value = "SELECT * FROM oshi_entity o WHERE o.name LIKE CONCAT('%', :keyword, '%') LIMIT :10", nativeQuery = true)
    List<OshiEntity> searchOshiListWithLimit(@Param("keyword") String keyword );
}