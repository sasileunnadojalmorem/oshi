package com.oshi.ohsi_back.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    

    UserEntity findByEmail(String email);
    
}
