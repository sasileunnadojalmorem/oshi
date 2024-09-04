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
    @Query("SELECT o FROM OshiEntity o WHERE o.oshiId = :id")  // 엔터티 필드명에 맞춰 수정
    OshiEntity findByOshiId(@Param("id") int id);  // 메소드 이름도 카멜 케이스로 수정

    @Query(value = "SELECT * FROM oshi o WHERE o.name LIKE CONCAT('%', :keyword, '%') LIMIT :limit", nativeQuery = true)
    List<OshiEntity> searchOshiList(@Param("keyword") String keyword, @Param("limit") int limit);

}