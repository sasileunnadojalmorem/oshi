package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsInfoRequsetDto;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsRequestDto;
import com.oshi.ohsi_back.dto.response.goods.AddGoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsInfoResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.repository.BaseGoodsRepository;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.OshiRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.GoodsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GoodsServiceImplement implements GoodsService{
    private final UserRepository userRepository;
    private final BaseGoodsRepository baseGoodsRepository;
    private final OshiRepository oshiRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<? super AddGoodsResponseDto> AddGoods(AddGoodsRequestDto dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return AddGoodsResponseDto.notExistUser();
            int id = userEntity.getUserid();
            boolean existsGoods = baseGoodsRepository.existsByName(dto.getName());
            if(existsGoods) return AddGoodsResponseDto.duplicationName();
            
            BaseGoodsEntity baseGoodsEntity = new BaseGoodsEntity(dto, id);
            baseGoodsRepository.save(baseGoodsEntity); // BaseGoodsEntity 객체를 저장
            return AddGoodsResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AddGoodsResponseDto.databaseError();   
        }
    }

    @Override
    public ResponseEntity<? super GetGoodsResponseDto> GetGoods(GetGoodsRequestDto dto) {
        int id = dto.getId();
        int page_size = 30;
        Sort sortOrder;
        Page<BaseGoodsEntity> page = null;

        try {
            sortOrder = "recent".equals(dto.getSortOrder()) 
                                ? Sort.by("goodsId").descending() // goods_id를 goodsId로 수정
                                : Sort.by("goodsId").ascending();

            Pageable pageable = PageRequest.of(dto.getPagenum(), page_size, sortOrder);

            if ("OSHI".equals(dto.getMethod())) {
                boolean existsOshi = oshiRepository.existsById(id);
                if (!existsOshi) return GetGoodsResponseDto.notExistBoard();
                page = baseGoodsRepository.findByOshiId(id, pageable); // findByOshiId 사용
            } else if ("CATEGORY".equals(dto.getMethod())) {
                boolean existsCategory = categoryRepository.existsById(id);
                if (!existsCategory) return GetGoodsResponseDto.notExistBoard();
                page = baseGoodsRepository.findByCategoryId(id, pageable); // findByCategoryId 사용
            } else {
                return GetGoodsResponseDto.databaseError();
                 // 유효하지 않은 method 처리
            }

            return GetGoodsResponseDto.success(page);
        } catch (Exception e) {
            e.printStackTrace();
            return GetGoodsResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetGoodsInfoResponseDto> GetGoodsInfo(GetGoodsInfoRequsetDto dto) {
       try {
        boolean existsGoods = baseGoodsRepository.existsByGoodsId(dto.getGoodsId());
        if(!existsGoods) return GetGoodsInfoResponseDto.notExistBoard();
        BaseGoodsEntity baseGoodsEntity = baseGoodsRepository.findByGoodsId(dto.getGoodsId());
        return GetGoodsInfoResponseDto.success(baseGoodsEntity);
       
    
    } catch (Exception e) {
        e.printStackTrace();
        return GetGoodsInfoResponseDto.databaseError();    // DB 에러
       }
    }
}