package com.oshi.ohsi_back.domain.sale.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.sale.domain.entity.SaleStatusEntity;

@Repository
public interface SaleStatusRepository extends JpaRepository<SaleStatusEntity, Integer> {
}
