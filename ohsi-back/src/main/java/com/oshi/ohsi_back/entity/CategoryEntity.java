package com.oshi.ohsi_back.entity;

import javax.persistence.*;
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
    private int category_id;

    @Column(name = "oshi_id", nullable = false)
    private int oshi_id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CategoryType type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "author_id")
    private int author_id;

    public enum CategoryType {
        OFFICIAL, NONOFFICIAL
    }
}
    