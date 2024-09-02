package com.oshi.ohsi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.UserOshiEntity;

@Repository
public interface UserOshiRepository extends JpaRepository<UserOshiEntity, Integer> {
@Query("SELECT u FROM UserOshiEntity u WHERE u.user_id = :userId AND u.oshi_id = :oshiId")
UserOshiEntity findByUser_idAndOshi_id(@Param("userId") int userId, @Param("oshiId") int oshiId);
}
