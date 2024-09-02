package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.dto.request.sale.AddSaleRequestDto;

import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private int sales_id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "oshi_id", nullable = false)
    private int oshi_id;

    @Column(name = "category_id", nullable = false)
    private int category_id;

    @Column(name = "goods_id", nullable = false)
    private int goods_id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "status", nullable = false)
    private Salestatus salestatus;

    private enum Salestatus{
        NONSALE,RESERVE,SALE

    }

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_id")
    private Integer image_id;

    public SaleEntity(AddSaleRequestDto dto,int id){
        this.oshi_id = dto.getOshi_id();
        this.category_id = dto.getCategory_id();
        this.goods_id = dto.getGoods_id();
        this.price = dto.getPrice();
        this.salestatus = Salestatus.SALE;
        this.description = dto.getDescription();
        this.user_id = id;
        Integer image_id = dto.getImage_id();
        if(image_id != null) {
            this.image_id = dto.getImage_id();
        }
        
    }
}
