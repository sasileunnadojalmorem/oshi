package com.oshi.ohsi_back.repository.OshiRepository;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.oshi.ohsi_back.entity.QImageEntity;
import com.oshi.ohsi_back.entity.QOshiEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OshiRepositoryCustomImpl implements OshiRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public OshiResponseDto findImageUrlOshiEntity(int oshiId) {
        QOshiEntity oshi = QOshiEntity.oshiEntity;
        QImageEntity image = QImageEntity.imageEntity;

        return queryFactory
            .select(Projections.constructor(OshiResponseDto.class, image.url,oshi))
            .from(oshi)
            .leftJoin(image).on(oshi.oshiId.eq(image.relatedId)
                                .and(image.relatedType.eq(ImageType.oshi)))
            .where(oshi.oshiId.eq(oshiId))  // 이미지가 있을 때만 반환
            .fetchOne();
    }
}