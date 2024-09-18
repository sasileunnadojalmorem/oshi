package com.oshi.ohsi_back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseGoodsEntity is a Querydsl query type for BaseGoodsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBaseGoodsEntity extends EntityPathBase<BaseGoodsEntity> {

    private static final long serialVersionUID = -848923152L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseGoodsEntity baseGoodsEntity = new QBaseGoodsEntity("baseGoodsEntity");

    public final QCategoryEntity category;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> favoriteCount = createNumber("favoriteCount", Integer.class);

    public final NumberPath<Integer> goodsId = createNumber("goodsId", Integer.class);

    public final QImageEntity image;

    public final StringPath name = createString("name");

    public final QOshiEntity oshi;

    public final QGoodsTypeEntity type;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public final QUserEntity writer;

    public QBaseGoodsEntity(String variable) {
        this(BaseGoodsEntity.class, forVariable(variable), INITS);
    }

    public QBaseGoodsEntity(Path<? extends BaseGoodsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseGoodsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBaseGoodsEntity(PathMetadata metadata, PathInits inits) {
        this(BaseGoodsEntity.class, metadata, inits);
    }

    public QBaseGoodsEntity(Class<? extends BaseGoodsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategoryEntity(forProperty("category"), inits.get("category")) : null;
        this.image = inits.isInitialized("image") ? new QImageEntity(forProperty("image")) : null;
        this.oshi = inits.isInitialized("oshi") ? new QOshiEntity(forProperty("oshi"), inits.get("oshi")) : null;
        this.type = inits.isInitialized("type") ? new QGoodsTypeEntity(forProperty("type")) : null;
        this.writer = inits.isInitialized("writer") ? new QUserEntity(forProperty("writer"), inits.get("writer")) : null;
    }

}

