package com.oshi.ohsi_back.repository.SaleRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.SaleResponseDto;

public interface SaleRepositoryCustom {

    Page<SaleResponseDto> getSaleList(Pageable pageable,GetSaleListRequestDto dto);
}