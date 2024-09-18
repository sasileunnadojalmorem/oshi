package com.oshi.ohsi_back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.dto.response.category.CategoryResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    
    boolean existsByName(String name);
    
    CategoryEntity findByCategoryId(int categoryId);
    CategoryEntity findByName(String name);
    // OshiEntity의 oshiId로 조회
    Page<CategoryEntity> findByOshi_OshiId(int oshiId, Pageable pageable);

    @Query("SELECT new com.oshi.ohsi_back.dto.response.category.CategoryResponseDto(i.url, c) " +
       "FROM CategoryEntity c " +
       "LEFT JOIN ImageEntity i ON c.categoryId = i.relatedId AND i.relatedType = 'category' " +
       "WHERE c.name LIKE CONCAT('%', :keyword, '%')")
    Page<CategoryResponseDto> searchCategory(@Param("keyword") String keyword, Pageable pageable);


}