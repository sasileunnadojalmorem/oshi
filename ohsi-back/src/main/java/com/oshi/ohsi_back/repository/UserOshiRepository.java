package com.oshi.ohsi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.UserOshiEntity;

@Repository
public interface UserOshiRepository extends JpaRepository<UserOshiEntity, Integer> {
}
