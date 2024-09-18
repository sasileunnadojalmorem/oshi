    package com.oshi.ohsi_back.repository.SaleRepository;

    import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import com.oshi.ohsi_back.dto.request.sale.GetSaleListRequestDto;
    import com.oshi.ohsi_back.dto.response.Sale.SaleResponseDto;
    import com.querydsl.core.types.OrderSpecifier;
    import com.querydsl.core.types.Projections;
    import com.oshi.ohsi_back.entity.QSaleEntity;
    import com.oshi.ohsi_back.entity.QOshiEntity;
    import com.oshi.ohsi_back.entity.QCategoryEntity;
    import com.oshi.ohsi_back.entity.QBaseGoodsEntity;
    import com.oshi.ohsi_back.entity.QImageEntity;
    import com.querydsl.core.types.dsl.BooleanExpression;
    import com.querydsl.core.QueryResults;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageImpl;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    @RequiredArgsConstructor
    public class SaleRepositoryCustomImpl implements SaleRepositoryCustom {

        private final JPAQueryFactory queryFactory;

        @Override
        public Page<SaleResponseDto> getSaleList(Pageable pageable, GetSaleListRequestDto dto) {
            QSaleEntity sale = QSaleEntity.saleEntity;
            QOshiEntity oshi = QOshiEntity.oshiEntity;
            QCategoryEntity category = QCategoryEntity.categoryEntity;
            QBaseGoodsEntity goods = QBaseGoodsEntity.baseGoodsEntity;
            QImageEntity image = QImageEntity.imageEntity;

            String method = dto.getMethod();
            int id = dto.getTypeId();
            String sortOrder = dto.getSortOrder();

            // 동적 where 절 생성
            BooleanExpression whereClause = createWhereClause(method, id);

            // 정렬 조건 생성
            OrderSpecifier<?> sort = sortOrderBtClause(sortOrder);

            // 데이터 페치 및 페이징 정보
            QueryResults<SaleResponseDto> queryResults = queryFactory
            .select(Projections.constructor(SaleResponseDto.class,
                sale.salesId,
                sale.user.userId,
                sale.user.username,
                sale.oshi.oshiId,
                sale.oshi.name,
                sale.category.categoryId,
                sale.category.name,
                sale.goods.goodsId,
                sale.goods.name,
                sale.price,
                sale.status.stringValue(),  // Enum을 문자열로 변환
                sale.image.id,
                sale.description,
                sale.image.url
            ))
            .from(sale)
            .leftJoin(sale.oshi, oshi)
            .leftJoin(sale.category, category)
            .leftJoin(sale.goods, goods)
            .leftJoin(sale.image, image)
            .where(whereClause)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(sort)
            .fetchResults();

            // 결과 리스트 및 전체 개수 추출
            List<SaleResponseDto> content = queryResults.getResults();
            long total = queryResults.getTotal();

            // Page 객체로 반환
            return new PageImpl<>(content, pageable, total);
        }

        // 동적 where 절을 만드는 메서드
        private BooleanExpression createWhereClause(String method, int id) {
            QSaleEntity sale = QSaleEntity.saleEntity;
            BooleanExpression whereClause = sale.isNotNull();  // 기본 조건 (모든 데이터를 포함)
            if ("oshi".equals(method)) {
                whereClause = whereClause.and(sale.oshi.oshiId.eq(id));
            } else if ("category".equals(method)) {
                whereClause = whereClause.and(sale.category.categoryId.eq(id));
            } else if ("goods".equals(method)) {
                whereClause = whereClause.and(sale.goods.goodsId.eq(id));
            }
            return whereClause;
        }

        // 정렬 조건을 동적으로 생성하는 메서드
        private OrderSpecifier<?> sortOrderBtClause(String sortOrder){
            QSaleEntity sale = QSaleEntity.saleEntity;
            if("recent".equals(sortOrder)){
                return sale.salesId.desc();  // 최신순
            } else if("price".equals(sortOrder)){
                return sale.price.asc();  // 가격순
            }
            return sale.salesId.asc();  // 기본 정렬
        }
    }