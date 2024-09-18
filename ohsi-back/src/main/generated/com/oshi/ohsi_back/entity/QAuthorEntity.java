package com.oshi.ohsi_back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthorEntity is a Querydsl query type for AuthorEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthorEntity extends EntityPathBase<AuthorEntity> {

    private static final long serialVersionUID = 31923110L;

    public static final QAuthorEntity authorEntity = new QAuthorEntity("authorEntity");

    public final ListPath<CategoryEntity, QCategoryEntity> categories = this.<CategoryEntity, QCategoryEntity>createList("categories", CategoryEntity.class, QCategoryEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QAuthorEntity(String variable) {
        super(AuthorEntity.class, forVariable(variable));
    }

    public QAuthorEntity(Path<? extends AuthorEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorEntity(PathMetadata metadata) {
        super(AuthorEntity.class, metadata);
    }

}

