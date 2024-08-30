package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
import com.oshi.ohsi_back.dto.request.oshi.oshiRequestDto;

import lombok.*;



@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ImageType type;

    @Column(name = "reference_id", nullable = false)
    private int reference_id;

    public ImageEntity(oshiRequestDto dto,Integer id)
    {
        this.type = ImageType.OSHI;
        this.reference_id = id;
        this.url = dto.getProfileImageUrl();    


   }

    public ImageEntity(ImageRequestDto dto){
        this.type = dto.getType();
        this.reference_id = dto.getReferenceId();
    }

}
