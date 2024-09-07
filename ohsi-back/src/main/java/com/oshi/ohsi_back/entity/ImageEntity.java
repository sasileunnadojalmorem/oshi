package com.oshi.ohsi_back.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "url", nullable = false)
    private String url;

  

    @OneToMany(mappedBy = "profileImage", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<SaleEntity> sales;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<OshiEntity> oshis;

    // DTO를 사용한 생성자
    public ImageEntity(String url) {
        this.url = url;
        
    }
}