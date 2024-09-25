package com.oshi.ohsi_back.domain.sale.infrastructure.SaleRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oshi.ohsi_back.domain.sale.presentation.dto.request.GetSaleListRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.response.SaleResponseDto;

public interface SaleRepositoryCustom {

    Page<SaleResponseDto> getSaleList(Pageable pageable,GetSaleListRequestDto dto);
}