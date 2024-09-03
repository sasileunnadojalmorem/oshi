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
    private int oshiId;  // 필드명 카멜 케이스로 수정

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "image_id")
    private int imageId;  // 필드명 카멜 케이스로 수정

    public OshiEntity(oshiRequestDto dto){
        this.description = dto.getDescription();
        this.name = dto.getName();
        this.imageId = dto.getImageId();  // 필드명 수정
    }
}