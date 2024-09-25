package com.oshi.ohsi_back.domain.goods.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsTypeEntity is a Querydsl query type for GoodsTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoodsTypeEntity extends EntityPathBase<GoodsTypeEntity> {

    private static final long serialVersionUID = 1987647107L;

    public static final QGoodsTypeEntity goodsTypeEntity = new QGoodsTypeEntity("goodsTypeEntity");

    public final ListPath<BaseGoodsEntity, QBaseGoodsEntity> goods = this.<BaseGoodsEntity, QBaseGoodsEntity>createList("goods", BaseGoodsEntity.class, QBaseGoodsEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QGoodsTypeEntity(String variable) {
        super(GoodsTypeEntity.class, forVariable(variable));
    }

    public QGoodsTypeEntity(Path<? extends GoodsTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsTypeEntity(PathMetadata metadata) {
        super(GoodsTypeEntity.class, metadata);
    }

}

