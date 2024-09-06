package com.oshi.ohsi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.UserOshiEntity;

@Repository
public interface UserOshiRepository extends JpaRepository<UserOshiEntity, Integer> {
    
    // user와 oshi의 ID로 UserOshiEntity 찾기
    @Query("SELECT u FROM UserOshiEntity u WHERE u.user.userId = :userId AND u.oshi.oshiId = :oshiId")
    UserOshiEntity findByUser_idAndOshi_id(@Param("userId") int userId, @Param("oshiId") int oshiId);

    // user ID로 UserOshiEntity 리스트 찾기
    @Query("SELECT u FROM UserOshiEntity u WHERE u.user.userId = :userId")
    List<UserOshiEntity> findByUser(@Param("userId") int userId);
}