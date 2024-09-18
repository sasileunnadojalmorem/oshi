package com.oshi.ohsi_back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSaleStatusEntity is a Querydsl query type for SaleStatusEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaleStatusEntity extends EntityPathBase<SaleStatusEntity> {

    private static final long serialVersionUID = -454027596L;

    public static final QSaleStatusEntity saleStatusEntity = new QSaleStatusEntity("saleStatusEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QSaleStatusEntity(String variable) {
        super(SaleStatusEntity.class, forVariable(variable));
    }

    public QSaleStatusEntity(Path<? extends SaleStatusEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSaleStatusEntity(PathMetadata metadata) {
        super(SaleStatusEntity.class, metadata);
    }

}

