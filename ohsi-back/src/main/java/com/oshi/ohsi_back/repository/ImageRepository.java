package com.oshi.ohsi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    
    ImageEntity findById(int id);
    boolean existsById(int id);
    List<ImageEntity> findByRelatedIdAndRelatedType(int relatedId, ImageType relatedType);

}