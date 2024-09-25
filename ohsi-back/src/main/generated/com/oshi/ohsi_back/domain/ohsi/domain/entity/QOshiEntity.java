package com.oshi.ohsi_back.domain.ohsi.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOshiEntity is a Querydsl query type for OshiEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOshiEntity extends EntityPathBase<OshiEntity> {

    private static final long serialVersionUID = 2074095505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOshiEntity oshiEntity = new QOshiEntity("oshiEntity");

    public final ListPath<com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity, com.oshi.ohsi_back.domain.category.domain.entity.QCategoryEntity> categories = this.<com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity, com.oshi.ohsi_back.domain.category.domain.entity.QCategoryEntity>createList("categories", com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity.class, com.oshi.ohsi_back.domain.category.domain.entity.QCategoryEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity image;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> oshiId = createNumber("oshiId", Integer.class);

    public final ListPath<com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity, com.oshi.ohsi_back.domain.sale.domain.entity.QSaleEntity> sales = this.<com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity, com.oshi.ohsi_back.domain.sale.domain.entity.QSaleEntity>createList("sales", com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity.class, com.oshi.ohsi_back.domain.sale.domain.entity.QSaleEntity.class, PathInits.DIRECT2);

    public QOshiEntity(String variable) {
        this(OshiEntity.class, forVariable(variable), INITS);
    }

    public QOshiEntity(Path<? extends OshiEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOshiEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOshiEntity(PathMetadata metadata, PathInits inits) {
        this(OshiEntity.class, metadata, inits);
    }

    public QOshiEntity(Class<? extends OshiEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity(forProperty("image")) : null;
    }

}

