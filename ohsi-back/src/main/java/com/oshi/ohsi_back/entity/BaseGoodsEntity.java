package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.dto.request.goods.AddGoodsRequestDto;
import com.oshi.ohsi_back.repository.UserRepository;
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

    @Column(name = "type_id", nullable = false)
    private int type;

    @Column(name = "writer_id", nullable = false)
    private int writer_id;

    @Column(name = "image_id")
    private Integer image_id;

    public BaseGoodsEntity(AddGoodsRequestDto dto, int userid) {
        this.oshi_id = dto.getOshi_id();
        this.category_id = dto.getCategory_id();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.view_count = 0;
        this.favorite_count = 0;
        this.type = dto.getType();
        this.image_id = dto.getImage_id();
        this.writer_id = userid;
        
        
    }
}
