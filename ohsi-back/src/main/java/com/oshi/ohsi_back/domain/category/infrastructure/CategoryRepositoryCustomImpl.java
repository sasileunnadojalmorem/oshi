



package com.oshi.ohsi_back.domain.category.infrastructure;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.oshi.ohsi_back.domain.category.domain.entity.QCategoryEntity;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.GetCategoryListRequseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.CategoryResponseDto;
import com.oshi.ohsi_back.domain.category.presentation.dto.response.GetCategoryListResponseDto;
import com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity;
import com.oshi.ohsi_back.domain.image.domain.enums.ImageType;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public GetCategoryListResponseDto GetCategoryList(GetCategoryListRequseDto dto, Pageable pageable) {
       
        QCategoryEntity category = QCategoryEntity.categoryEntity;
        QImageEntity imageEntity = QImageEntity
        .imageEntity;
        
        String sortOrder = dto.getSortOrder();

        OrderSpecifier<?> sort = sortOrderBtClause(sortOrder);

        // QueryDsl을 사용한 쿼리 실행
        QueryResults<CategoryResponseDto> results = queryFactory
            .select(Projections.constructor(
                CategoryResponseDto.class,
                
                imageEntity.url,
                category
            ))
            .from(category)
            .leftJoin(imageEntity).on(category.categoryId.eq(imageEntity.relatedId)
                .and(imageEntity.relatedType.eq(ImageType.category)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(sort)  // categoryId 기준으로 정렬
            .fetchResults();
        
        // 총 개수와 페이지 수 계산
        long total = results.getTotal();
        int totalPages = (int) ((total + pageable.getPageSize() - 1) / pageable.getPageSize()); // 전체 페이지 수 계산
        int currentPage = pageable.getPageNumber(); // 현재 페이지 번호

        // GetCategoryListResponseDto에 결과 매핑
        return GetCategoryListResponseDto.builder()
            .categoryList(results.getResults())  // 카테고리 리스트
            .totalCount((int) total)             // 총 카운트
            .totalPages(totalPages)              // 총 페이지 수
            .currentPage(currentPage)            // 현재 페이지
            .build();
    }

    private OrderSpecifier<?> sortOrderBtClause(String sortOrder){
            QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;

            if("recentdesc".equals(sortOrder)){
                return categoryEntity.categoryId.desc();  // 최신순
            } else if("recentasc".equals(sortOrder)){
                return categoryEntity.categoryId.asc();  // 가격순
            }
            return categoryEntity.categoryId.asc();  // 기본 정렬
        }
}