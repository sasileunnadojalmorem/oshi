package com.oshi.ohsi_back.domain.ohsi.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;

@Repository
public interface OshiRepository extends JpaRepository<OshiEntity, Integer>, OshiRepositoryCustom {
    boolean existsByName(String name);
    
    boolean existsByOshiId(int oshiId);  
    
    OshiEntity findByOshiId(int oshiId);
    OshiEntity findByName(String name);

    @Query("SELECT new com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto(i.url, o) " +
           "FROM ImageEntity i " +
           "JOIN OshiEntity o ON o.oshiId = i.relatedId " +
           "WHERE o.oshiId = :oshiId AND i.relatedType = 'OSHI'")
    OshiResponseDto findImageUrlAndOshiByOshiId(@Param("oshiId") int oshiId);

    @Query("SELECT new com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto(i.url, o, 0) " +
           "FROM OshiEntity o " +
           "LEFT JOIN ImageEntity i ON o.oshiId = i.relatedId AND i.relatedType = 'OSHI' " +
           "WHERE o.name LIKE CONCAT('%', :keyword, '%')")
    Page<OshiResponseDto> searchOshiList(@Param("keyword") String keyword, Pageable pageable);
}