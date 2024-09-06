package com.oshi.ohsi_back.entity;

import javax.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "goods_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<BaseGoodsEntity> goods;
}