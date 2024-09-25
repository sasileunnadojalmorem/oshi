    package com.oshi.ohsi_back.domain.ohsi.infrastructure;

    import com.querydsl.core.types.Projections;
    import com.querydsl.core.types.dsl.CaseBuilder;
    import com.querydsl.core.types.dsl.Expressions;
    import com.querydsl.jpa.impl.JPAQueryFactory;
import com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.QUserOshiEntity;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetUserOshiResponseDto;

import lombok.RequiredArgsConstructor;

    import java.util.List;

    import org.springframework.stereotype.Repository;
    @Repository
    @RequiredArgsConstructor
    public class OshiRepositoryCustomImpl implements OshiRepositoryCustom {
    
        private final JPAQueryFactory queryFactory;
    
        @Override
        public OshiResponseDto findImageUrlOshiEntity(int oshiId, Integer userId) {
            QOshiEntity oshi = QOshiEntity.oshiEntity;
            QImageEntity image = QImageEntity.imageEntity;
            QUserOshiEntity userOshi = QUserOshiEntity.userOshiEntity;
    
            // 유저-오시 관계 여부 확인 서브쿼리 (userId가 있을 때만)
            Integer status = (userId != null) ? queryFactory
                .select(new CaseBuilder()
                .when(userOshi.count().gt(0)).then(1)  // 유저-오시 관계가 있으면 1
                .otherwise(0))                  .from(userOshi)
                .where(userOshi.user.userId.eq(userId)
                       .and(userOshi.oshi.oshiId.eq(oshiId)))
                .fetchOne() : 0;  // userId가 없으면 0 반환
    
            return queryFactory
                .select(Projections.constructor(OshiResponseDto.class,
                        image.url,     // 이미지 URL
                        oshi,          // 오시 엔티티
                        Expressions.asNumber(status)))  // 상태값 반환 (1 또는 0)
                .from(oshi)
                .leftJoin(image).on(oshi.oshiId.eq(image.relatedId)
                                    .and(image.relatedType.eq(ImageType.oshi)))
                .where(oshi.oshiId.eq(oshiId))
                .fetchOne();
        }
    
        @Override
        public GetUserOshiResponseDto findByUserId(int userId) {
            QUserOshiEntity userOshi = QUserOshiEntity.userOshiEntity;
            QOshiEntity oshi = QOshiEntity.oshiEntity;
            QImageEntity image = QImageEntity.imageEntity;
    
            // 유저 ID 기준으로 유저의 오시 리스트 반환
            List<OshiResponseDto> oshiList = queryFactory
                .select(Projections.constructor(OshiResponseDto.class,
                        image.url, oshi))  // OshiResponseDto로 매핑
                .from(userOshi)
                .join(oshi).on(userOshi.oshi.oshiId.eq(oshi.oshiId))
                .leftJoin(image).on(oshi.oshiId.eq(image.relatedId)
                                    .and(image.relatedType.eq(ImageType.oshi)))
                .where(userOshi.user.userId.eq(userId))  // 유저 ID 조건
                .fetch();
    
            // 결과를 GetUserOshiResponseDto로 매핑
            return GetUserOshiResponseDto.builder()
                    .oshiEntities(oshiList)
                    .build();
        }
    }