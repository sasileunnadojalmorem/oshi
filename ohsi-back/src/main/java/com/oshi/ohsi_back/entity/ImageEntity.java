package com.oshi.ohsi_back.entity;

import javax.persistence.*;
import lombok.*;
import java.util.List;
import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;

@Entity
@Table(name = "images")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ImageType type;

    @Column(name = "reference_id", nullable = false)
    private int referenceId;

    @OneToMany(mappedBy = "profileImage", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<SaleEntity> sales;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<OshiEntity> oshis;

    // DTO를 사용한 생성자
    public ImageEntity(ImageRequestDto dto, String url) {
        this.referenceId = dto.getReferenceId();
        this.url = url;
        this.type = dto.getType();
    }
}