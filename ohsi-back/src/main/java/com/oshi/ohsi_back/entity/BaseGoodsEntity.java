package com.oshi.ohsi_back.entity;

import javax.persistence.*;
import lombok.*;
import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;

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
    public BaseGoodsEntity(AddGoodsRequestDto dto, UserEntity writer) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.writer = writer;

        // 연관된 엔터티들의 ID만 설정
        this.oshi = new OshiEntity();
        this.oshi.setOshiId(dto.getOshi_id());

        this.category = new CategoryEntity();
        this.category.setCategoryId(dto.getCategory_id());

        this.type = new GoodsTypeEntity();
        this.type.setId(dto.getType());

        if(dto.getImage_id() != null) {
            this.image = new ImageEntity();
            this.image.setId(dto.getImage_id());
        }
    }
}