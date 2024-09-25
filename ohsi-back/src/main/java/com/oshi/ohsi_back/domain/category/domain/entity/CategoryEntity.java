

package com.oshi.ohsi_back.domain.category.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.oshi.ohsi_back.domain.Author.domain.entity.AuthorEntity;
import com.oshi.ohsi_back.domain.category.presentation.dto.request.AddCategoryRequestDto;
import com.oshi.ohsi_back.domain.goods.domain.entity.BaseGoodsEntity;
import com.oshi.ohsi_back.domain.image.domain.entity.ImageEntity;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    private int categoryId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CategoryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oshi_id", referencedColumnName = "oshi_id", nullable = false)
    private OshiEntity oshi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<BaseGoodsEntity> goods;

    // DTO를 사용한 생성자
    public CategoryEntity(AddCategoryRequestDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.type = dto.getType().equals("OFFICIAL") ? CategoryType.OFFICIAL : CategoryType.NONOFFICIAL;
        this.oshi = new OshiEntity();
        this.oshi.setOshiId(dto.getOshiId()); 
        // 연관된 엔터티들의 ID만 설정
        if(dto.getAuthorId() != null) {
            this.author = new AuthorEntity();
            this.author.setId(dto.getAuthorId());
        }

    }

    public enum CategoryType {
        OFFICIAL, NONOFFICIAL
    }
}