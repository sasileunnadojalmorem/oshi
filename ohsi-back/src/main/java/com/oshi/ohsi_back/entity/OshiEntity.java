package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;

import lombok.*;

@Entity
@Table(name = "oshi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OshiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oshi_id")
    private int oshi_id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "image_url")
    private String profileImageUrl;

    public OshiEntity(oshiRequestDto dto){
        this.description = dto.getDescription();
        this.name = dto.getName();
        this.profileImageUrl = dto.getProfileImageUrl();

    }
}
