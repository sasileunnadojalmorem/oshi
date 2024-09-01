package com.oshi.ohsi_back.service.implement;

import org.apache.tomcat.jni.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.repository.BaseGoodsRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.GoodsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GoodsServiceImplement implements GoodsService{
    private final UserRepository userRepository;
    private final BaseGoodsRepository baseGoodsRepository;
    @Override
    public ResponseEntity<? super AddGoodsResponseDto> AddOshi(AddGoodsRequestDto dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return AddGoodsResponseDto.notExistUser();
            int id = userEntity.getUserid();
            boolean existsGoods = baseGoodsRepository.existsByName(dto.getName());
            if(existsGoods == true) return AddGoodsResponseDto.duplicationName();
            BaseGoodsEntity baseGoodsEntity = new BaseGoodsEntity(dto, id);
            baseGoodsRepository.save(baseGoodsEntity); // BaseGoodsEntity 객체를 저장
            return AddGoodsResponseDto.success();

            
        } catch (Exception e) {
            e.printStackTrace();
            return AddGoodsResponseDto.databaseError();   
        }
    }
    
}
