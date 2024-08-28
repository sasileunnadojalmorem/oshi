package com.oshi.ohsi_back.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "goods_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
