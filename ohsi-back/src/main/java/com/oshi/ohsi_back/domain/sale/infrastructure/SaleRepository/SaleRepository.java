package com.oshi.ohsi_back.domain.sale.infrastructure.SaleRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Integer>,SaleRepositoryCustom {
    Optional<SaleEntity> findBySalesId(int id);
}
