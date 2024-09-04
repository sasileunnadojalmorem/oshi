package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import lombok.*;

@Entity
@Table(name = "base_goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseGoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private int goodsId;

    @Column(name = "oshi_id", nullable = false)
    private int oshiId;

    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "favorite_count", nullable = false)
    private int favoriteCount;

    @Column(name = "type_id", nullable = false)
    private int typeId;

    @Column(name = "writer_id", nullable = false)
    private int writerId;

    @Column(name = "image_id")
    private Integer imageId;

    // DTO를 사용한 생성자 (DTO의 필드명은 언더바 유지)
    public BaseGoodsEntity(AddGoodsRequestDto dto, int userId) {
        this.oshiId = dto.getOshi_id(); // DTO의 언더바 유지
        this.categoryId = dto.getCategory_id(); // DTO의 언더바 유지
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.viewCount = 0; // 기본값 0
        this.favoriteCount = 0; // 기본값 0
        this.typeId = dto.getType();
        this.imageId = dto.getImage_id(); // DTO의 언더바 유지
        this.writerId = userId; // 이 필드는 서비스 로직에서 받아온 사용자 ID로 설정
    }
}