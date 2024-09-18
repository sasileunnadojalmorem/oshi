package com.oshi.ohsi_back.repository.GoodsRepositoy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.goods.GetGoodsListRequestDto;
import com.oshi.ohsi_back.dto.response.goods.GetGoodsListResponseDto;
import com.oshi.ohsi_back.dto.response.goods.GoodsResponseDto;
import com.oshi.ohsi_back.dto.response.goods.SearchGoodsResponseDto;
import com.oshi.ohsi_back.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.entity.ImageEntity;
import com.oshi.ohsi_back.entity.QBaseGoodsEntity;
import com.oshi.ohsi_back.entity.QImageEntity;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GoodsRepositoryCustomImpl implements GoodsRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public SearchGoodsResponseDto searchGoods(String keyword, Pageable pageable) {
        QBaseGoodsEntity baseGoods = QBaseGoodsEntity.baseGoodsEntity;
        QImageEntity image = QImageEntity.imageEntity;

        // 1. 데이터 조회
        List<Tuple> results = queryFactory
            .select(baseGoods, image)
            .from(baseGoods)
            .leftJoin(image).on(baseGoods.goodsId.eq(image.relatedId)
                .and(image.relatedType.eq(ImageType.goods)))
            .where(baseGoods.name.contains(keyword))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        // 2. 데이터 그룹화
        Map<BaseGoodsEntity, List<ImageEntity>> groupedData = results.stream()
            .collect(Collectors.groupingBy(
                tuple -> tuple.get(baseGoods),
                Collectors.mapping(
                    tuple -> tuple.get(image),
                    Collectors.toList()
                )
            ));

        // 3. DTO 변환
        List<GoodsResponseDto> goodsList = groupedData.entrySet().stream()
            .map(entry -> new GoodsResponseDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        
        return SearchGoodsResponseDto.builder()
                .baseGoodsEntities(goodsList)
                .build();
    }

    @Override
    public GetGoodsListResponseDto findGoods(GetGoodsListRequestDto dto, Pageable pageable) {
    QBaseGoodsEntity baseGoods = QBaseGoodsEntity.baseGoodsEntity;
    QImageEntity image = QImageEntity.imageEntity;

    // 동적 where 절 생성
    BooleanExpression whereClause = createWhereClause(dto.getMethod(), dto.getTypeId());

    // 정렬 조건 생성
    OrderSpecifier<?> sort = sortOrderByClause(dto.getSortOrder());

    // 데이터 조회
    List<Tuple> results = queryFactory
        .select(baseGoods, image)
        .from(baseGoods)
        .leftJoin(image).on(baseGoods.goodsId.eq(image.relatedId).and(image.relatedType.eq(ImageType.goods)))
        .where(whereClause)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(sort)
        .fetch();

    // 데이터 그룹화
    Map<BaseGoodsEntity, List<ImageEntity>> groupedData = results.stream()
        .collect(Collectors.groupingBy(
            tuple -> tuple.get(baseGoods),
            Collectors.mapping(tuple -> tuple.get(image), Collectors.toList())
        ));

    // DTO 변환
    List<GoodsResponseDto> goodsList = groupedData.entrySet().stream()
        .map(entry -> new GoodsResponseDto(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());

    // 총 개수 계산
    long totalCount = queryFactory
        .selectFrom(baseGoods)
        .where(whereClause)
        .fetchCount();
    
    int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());

    // 결과 반환
    return GetGoodsListResponseDto.builder()
            .goodsEntities(goodsList)
            .totalPages(totalPages)
            .totalCount((int) totalCount)
            .currentPage((int)pageable.getOffset())
            .build();
}

// 동적 where 절 생성 메서드
private BooleanExpression createWhereClause(String method, int typeId) {
    QBaseGoodsEntity baseGoods = QBaseGoodsEntity.baseGoodsEntity;
    BooleanExpression whereClause = baseGoods.isNotNull();  // 기본 조건 (모든 데이터를 포함)

    if ("category".equals(method)) {
        whereClause = whereClause.and(baseGoods.category.categoryId.eq(typeId));
    } else if ("oshi".equals(method)) {
        whereClause = whereClause.and(baseGoods.oshi.oshiId.eq(typeId));
    }
    return whereClause;
}

// 정렬 조건 생성 메서드
private OrderSpecifier<?> sortOrderByClause(String sortOrder) {
        QBaseGoodsEntity baseGoods = QBaseGoodsEntity.baseGoodsEntity;
        if ("recent".equals(sortOrder)) {
            return baseGoods.goodsId.desc();  // 최신순
        } else if ("favorite".equals(sortOrder)) {
            return baseGoods.favoriteCount.asc();  // 가격 오름차순
        }
        return baseGoods.goodsId.asc();  // 기본 정렬
    }
}