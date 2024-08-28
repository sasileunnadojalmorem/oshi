package com.oshi.ohsi_back.entity;

import javax.persistence.*;
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
    private int goods_id;

    @Column(name = "oshi_id", nullable = false)
    private int oshi_id;

    @Column(name = "category_id", nullable = false)
    private int category_id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "view_count", nullable = false)
    private int view_count;

    @Column(name = "favorite_count", nullable = false)
    private int favorite_count;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "writer_id", nullable = false)
    private int writer_id;

    @Column(name = "image_id")
    private int image_id;
}
