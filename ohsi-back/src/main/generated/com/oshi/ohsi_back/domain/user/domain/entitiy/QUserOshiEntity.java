package com.oshi.ohsi_back.domain.user.domain.entitiy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserOshiEntity is a Querydsl query type for UserOshiEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserOshiEntity extends EntityPathBase<UserOshiEntity> {

    private static final long serialVersionUID = 899848601L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserOshiEntity userOshiEntity = new QUserOshiEntity("userOshiEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity oshi;

    public final QUserEntity user;

    public QUserOshiEntity(String variable) {
        this(UserOshiEntity.class, forVariable(variable), INITS);
    }

    public QUserOshiEntity(Path<? extends UserOshiEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserOshiEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserOshiEntity(PathMetadata metadata, PathInits inits) {
        this(UserOshiEntity.class, metadata, inits);
    }

    public QUserOshiEntity(Class<? extends UserOshiEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.oshi = inits.isInitialized("oshi") ? new com.oshi.ohsi_back.domain.ohsi.domain.entity.QOshiEntity(forProperty("oshi"), inits.get("oshi")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

