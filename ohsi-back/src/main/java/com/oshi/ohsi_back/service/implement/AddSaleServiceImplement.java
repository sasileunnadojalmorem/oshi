package com.oshi.ohsi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;
import com.oshi.ohsi_back.dto.response.Sale.AddSaleResponseDto;
import com.oshi.ohsi_back.entity.SaleEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.service.SaleService;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.SaleRepository;


import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AddSaleServiceImplement implements SaleService {
    
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;
    @Override
    public ResponseEntity<? super AddSaleResponseDto> addSale(AddSaleRequestDto dto, String email) {
        
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return AddSaleResponseDto.notExistUser();
            SaleEntity saleEntity = new SaleEntity(dto,userEntity);
            saleRepository.save(saleEntity);
            return AddSaleResponseDto.success(saleEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return AddSaleResponseDto.databaseError();
        }
    }


    
}
