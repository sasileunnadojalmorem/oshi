package com.oshi.ohsi_back.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id", updatable = false, nullable = false)
    private int salesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oshi_id", nullable = false)
    private OshiEntity oshi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", nullable = false)
    private BaseGoodsEntity goods;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SaleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // DTO를 사용한 생성자
    public SaleEntity(AddSaleRequestDto dto, UserEntity user) {
        this.price = dto.getPrice();
        this.status = SaleStatus.SALE; // 기본값 설정
        this.description = dto.getDescription();
        this.user = user;

        // 연관된 엔터티들의 ID만 설정
        this.oshi = new OshiEntity();
        this.oshi.setOshiId(dto.getOshi_id());

        this.category = new CategoryEntity();
        this.category.setCategoryId(dto.getCategory_id());

        this.goods = new BaseGoodsEntity();
        this.goods.setGoodsId(dto.getGoods_id());

        if(dto.getImage_id() != null) {
            this.image = new ImageEntity();
            this.image.setId(dto.getImage_id());
        }
    }

    public enum SaleStatus {
        NONSALE, RESERVE, SALE
    }
}