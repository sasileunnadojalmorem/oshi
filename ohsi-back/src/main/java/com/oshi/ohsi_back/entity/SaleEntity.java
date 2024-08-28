package com.oshi.ohsi_back.entity;

import javax.persistence.*;
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
    private int status;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_id")
    private int image_id;
}
