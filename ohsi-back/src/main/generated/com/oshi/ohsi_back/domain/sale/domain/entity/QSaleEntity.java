package com.oshi.ohsi_back.domain.sale.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaleEntity is a Querydsl query type for SaleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaleEntity extends EntityPathBase<SaleEntity> {

    private static final long serialVersionUID = 583895323L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleEntity saleEntity = new QSaleEntity("saleEntity");

    public final com.oshi.ohsi_back.domain.category.domain.entity.QCategoryEntity category;

    public final StringPath description = createString("description");

    public final com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity goods;

    public final com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity image;

    public final com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity oshi;

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> salesId = createNumber("salesId", Integer.class);

    public final EnumPath<com.oshi.ohsi_back.domain.sale.domain.enums.SaleStatusEnum> status = createEnum("status", com.oshi.ohsi_back.domain.sale.domain.enums.SaleStatusEnum.class);

    public final com.oshi.ohsi_back.domain.user.domain.entitiy.QUserEntity user;

    public QSaleEntity(String variable) {
        this(SaleEntity.class, forVariable(variable), INITS);
    }

    public QSaleEntity(Path<? extends SaleEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaleEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaleEntity(PathMetadata metadata, PathInits inits) {
        this(SaleEntity.class, metadata, inits);
    }

    public QSaleEntity(Class<? extends SaleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.oshi.ohsi_back.domain.category.domain.entity.QCategoryEntity(forProperty("category"), inits.get("category")) : null;
        this.goods = inits.isInitialized("goods") ? new com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity(forProperty("goods"), inits.get("goods")) : null;
        this.image = inits.isInitialized("image") ? new com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity(forProperty("image")) : null;
        this.oshi = inits.isInitialized("oshi") ? new com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity(forProperty("oshi"), inits.get("oshi")) : null;
        this.user = inits.isInitialized("user") ? new com.oshi.ohsi_back.domain.user.domain.entitiy.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

