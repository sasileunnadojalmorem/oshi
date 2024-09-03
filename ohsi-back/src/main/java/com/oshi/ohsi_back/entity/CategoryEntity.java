package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequsetDto;

import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "oshi_id", nullable = false)
    private int oshiId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CategoryType type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "author_id")
    private Integer author_id;

    public enum CategoryType {
        OFFICIAL, NONOFFICIAL
    }

    public CategoryEntity(AddCategoryRequsetDto dto){
        this.oshiId = dto.getOshiid();
        this.name = dto.getName();
        this.description = dto.getDescription() != null ? dto.getDescription() : ""; // 방어적 코드
        if ("NONOFFICIAL".equals(dto.getType())) {
            this.type = CategoryType.NONOFFICIAL;
            this.author_id = dto.getAuthorid();
        } else {
            this.type = CategoryType.OFFICIAL;
            this.author_id = null;
        }
    }
}
