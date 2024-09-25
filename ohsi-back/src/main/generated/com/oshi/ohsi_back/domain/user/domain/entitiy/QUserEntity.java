package com.oshi.ohsi_back.domain.user.domain.entitiy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -407987244L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath email = createString("email");

    public final ListPath<com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity, com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity> goods = this.<com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity, com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity>createList("goods", com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity.class, com.oshi.ohsi_back.domain.goods.domain.entity.QBaseGoodsEntity.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity profileImage;

    public final ListPath<com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity, com.oshi.ohsi_back.domain.sale.domain.entity.QSaleEntity> sales = this.<com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity, com.oshi.ohsi_back.domain.sale.domain.entity.QSaleEntity>createList("sales", com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity.class, com.oshi.ohsi_back.domain.sale.domain.entity.QSaleEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath username = createString("username");

    public final ListPath<UserOshiEntity, QUserOshiEntity> userOshis = this.<UserOshiEntity, QUserOshiEntity>createList("userOshis", UserOshiEntity.class, QUserOshiEntity.class, PathInits.DIRECT2);

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profileImage = inits.isInitialized("profileImage") ? new com.oshi.ohsi_back.domain.image.domain.entity.QImageEntity(forProperty("profileImage")) : null;
    }

}

