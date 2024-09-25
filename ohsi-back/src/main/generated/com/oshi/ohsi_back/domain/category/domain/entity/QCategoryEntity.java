package com.oshi.ohsi_back.domain.category.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryEntity is a Querydsl query type for CategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryEntity extends EntityPathBase<CategoryEntity> {

    private static final long serialVersionUID = 1518257723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryEntity categoryEntity = new QCategoryEntity("categoryEntity");

    public final com.oshi.ohsi_back.domain.Author.domain.entity.QAuthorEntity author;

    public final NumberPath<Integer> categoryId = createNumber("categoryId", Integer.class);

    public final StringPath description = createString("description");

    public final ListPath<com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity, com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity> goods = this.<com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity, com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity>createList("goods", com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity.class, com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity.class, PathInits.DIRECT2);

    public final com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity image;

    public final StringPath name = createString("name");

    public final com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity oshi;

    public final EnumPath<CategoryEntity.CategoryType> type = createEnum("type", CategoryEntity.CategoryType.class);

    public QCategoryEntity(String variable) {
        this(CategoryEntity.class, forVariable(variable), INITS);
    }

    public QCategoryEntity(Path<? extends CategoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryEntity(PathMetadata metadata, PathInits inits) {
        this(CategoryEntity.class, metadata, inits);
    }

    public QCategoryEntity(Class<? extends CategoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.oshi.ohsi_back.domain.Author.domain.entity.QAuthorEntity(forProperty("author")) : null;
        this.image = inits.isInitialized("image") ? new com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity(forProperty("image")) : null;
        this.oshi = inits.isInitialized("oshi") ? new com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity(forProperty("oshi"), inits.get("oshi")) : null;
    }

}

