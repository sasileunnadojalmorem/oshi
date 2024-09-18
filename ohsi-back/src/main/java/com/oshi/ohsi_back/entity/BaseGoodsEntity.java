package com.oshi.ohsi_back.entity;

import jakarta.persistence.*;
import lombok.*;
import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.dto.request.goods.UpdateGoodsRequestDto;

@Entity
@Table(name = "base_goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseGoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id", updatable = false, nullable = false)
    private int goodsId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;

    @Column(name = "favorite_count", nullable = false)
    private int favoriteCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oshi_id", nullable = false)
    private OshiEntity oshi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private GoodsTypeEntity type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private UserEntity writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    // DTO를 사용한 생성자
    public BaseGoodsEntity(AddGoodsRequestDto dto, UserEntity writer, OshiEntity oshiEntity, CategoryEntity categoryEntity) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.writer = writer; // 작성자 엔터티
    
        // 연관된 엔터티들의 객체를 직접 할당
        this.oshi = oshiEntity;  // OshiEntity 객체 설정
        this.category = categoryEntity;  // CategoryEntity 객체 설정
    
        // GoodsTypeEntity 설정 (dto로부터 타입 ID를 받아서 설정)
        this.type = new GoodsTypeEntity();
        this.type.setId(dto.getType());

    }
    public void updateGoods(UpdateGoodsRequestDto dto){
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.type = new GoodsTypeEntity();
        this.type.setId(dto.getType()); 
    }
}