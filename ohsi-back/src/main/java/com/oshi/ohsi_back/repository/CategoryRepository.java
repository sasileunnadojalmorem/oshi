package com.oshi.ohsi_back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    boolean existsByName(String name);
    CategoryEntity findByCategoryId(int categoryId);
    Page<CategoryEntity> findByOshiId(int oshiId, Pageable pageable);
}