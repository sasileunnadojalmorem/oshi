


package com.oshi.ohsi_back.domain.Author.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.oshi.ohsi_back.domain.category.domain.entity.CategoryEntity;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;
}