package com.oshi.ohsi_back.entity;

import javax.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;

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
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private ImageEntity image;

    @OneToMany(mappedBy = "oshi", fetch = FetchType.LAZY)
    private List<SaleEntity> sales;

    @OneToMany(mappedBy = "oshi", fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;

    // DTO를 사용한 생성자
    public OshiEntity(oshiRequestDto dto) {
        this.description = dto.getDescription();
        this.name = dto.getName();

        // ImageEntity 객체 생성 후 ID만 설정
        this.image = new ImageEntity();
        this.image.setId(dto.getImageId());
    }
}