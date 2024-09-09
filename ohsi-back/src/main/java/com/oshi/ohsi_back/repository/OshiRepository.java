package com.oshi.ohsi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.OshiEntity;

@Repository
public interface OshiRepository extends JpaRepository<OshiEntity, Integer> {
    boolean existsByName(String name);
    
    boolean existsByOshiId(int oshiId);  
    
    OshiEntity findByOshiId(int oshiId);
    
    @Query(value = "SELECT * FROM oshi o LEFT JOIN image i ON o.image_id = i.id WHERE o.name LIKE CONCAT('%', :keyword, '%') LIMIT :limit", nativeQuery = true)
    List<OshiEntity> searchOshiList(@Param("keyword") String keyword, @Param("limit") int limit);

}