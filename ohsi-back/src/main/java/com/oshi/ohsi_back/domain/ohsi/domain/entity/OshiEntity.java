package com.oshi.ohsi_back.domain.ohsi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.request.OshiRequestDto;
import com.oshi.ohsi_back.domain.sale.domain.entity.SaleEntity;

@Entity
@Table(name = "oshi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OshiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oshi_id", updatable = false, nullable = false)
    private int oshiId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @JsonIgnore
    private ImageEntity image;

    @OneToMany(mappedBy = "oshi", fetch = FetchType.LAZY)
    private List<SaleEntity> sales;

    @OneToMany(mappedBy = "oshi", fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;

    // DTO를 사용한 생성자
    public OshiEntity(OshiRequestDto dto) {
        this.description = dto.getDescription();
        this.name = dto.getName();
        // ImageEntity 객체 생성 후 ID만 설정 
    }
}