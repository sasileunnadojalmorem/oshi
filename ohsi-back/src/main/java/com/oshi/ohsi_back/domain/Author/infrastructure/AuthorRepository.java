package com.oshi.ohsi_back.domain.Author.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.Author.domain.entity.AuthorEntity;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    boolean existsByname(String name);
    boolean existsById(int id);
}
