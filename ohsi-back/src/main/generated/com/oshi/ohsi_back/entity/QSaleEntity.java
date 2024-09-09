package com.oshi.ohsi_back.entity;

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

    private static final long serialVersionUID = 1126200162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleEntity saleEntity = new QSaleEntity("saleEntity");

    public final QCategoryEntity category;

    public final StringPath description = createString("description");

    public final QBaseGoodsEntity goods;

    public final QImageEntity image;

    public final QOshiEntity oshi;

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> salesId = createNumber("salesId", Integer.class);

    public final EnumPath<SaleEntity.SaleStatus> status = createEnum("status", SaleEntity.SaleStatus.class);

    public final QUserEntity user;

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
        this.category = inits.isInitialized("category") ? new QCategoryEntity(forProperty("category"), inits.get("category")) : null;
        this.goods = inits.isInitialized("goods") ? new QBaseGoodsEntity(forProperty("goods"), inits.get("goods")) : null;
        this.image = inits.isInitialized("image") ? new QImageEntity(forProperty("image")) : null;
        this.oshi = inits.isInitialized("oshi") ? new QOshiEntity(forProperty("oshi"), inits.get("oshi")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

