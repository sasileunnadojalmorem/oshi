package com.oshi.ohsi_back.domain.sale.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;
import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;
import com.oshi.ohsi_back.domain.sale.domain.enums.SaleStatusEnum;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.AddSaleRequestDto;
import com.oshi.ohsi_back.domain.sale.presentation.dto.request.UpdateSaleRequestDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;

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
    private SaleStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // DTO를 사용한 생성자
    public SaleEntity(AddSaleRequestDto dto, UserEntity user,OshiEntity oshiEntity,CategoryEntity categoryEntity, BaseGoodsEntity baseGoodsEntity) {
        this.price = dto.getPrice();
        this.status = SaleStatusEnum.SALE; // 기본값 설정
        this.description = dto.getDescription();
        this.user = user;
        this.oshi = oshiEntity;
        this.category = categoryEntity;
        this.goods = baseGoodsEntity;
    }

    public void updateSale(UpdateSaleRequestDto dto) {
        this.price = dto.getPrice();           // 가격 업데이트
        this.status = dto.getState();          // 상태 업데이트
        this.description = dto.getDescription(); // 설명 업데이트
        
    }
    
}