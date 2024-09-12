package com.oshi.ohsi_back.repository.SaleRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oshi.ohsi_back.entity.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Integer>,SaleRepositoryCustom {
    Optional<SaleEntity> findBySalesId(int id);
}
