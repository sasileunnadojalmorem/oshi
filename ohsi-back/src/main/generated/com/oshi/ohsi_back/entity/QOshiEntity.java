package com.oshi.ohsi_back.entity;

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

    private static final long serialVersionUID = 316585600L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOshiEntity oshiEntity = new QOshiEntity("oshiEntity");

    public final ListPath<CategoryEntity, QCategoryEntity> categories = this.<CategoryEntity, QCategoryEntity>createList("categories", CategoryEntity.class, QCategoryEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final QImageEntity image;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> oshiId = createNumber("oshiId", Integer.class);

    public final ListPath<SaleEntity, QSaleEntity> sales = this.<SaleEntity, QSaleEntity>createList("sales", SaleEntity.class, QSaleEntity.class, PathInits.DIRECT2);

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
        this.image = inits.isInitialized("image") ? new QImageEntity(forProperty("image")) : null;
    }

}

