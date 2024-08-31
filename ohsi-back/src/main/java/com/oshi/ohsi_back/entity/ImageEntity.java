package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.common.ImageType;
import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;

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

    public ImageEntity(ImageRequestDto dto) {
        this.reference_id = dto.getReferenceId();
        this.url = dto.getUrl();

        // dto.getType()이 문자열로 전달되는 경우 이를 ImageType enum으로 변환
        if ("OSHI".equals(dto.getType())) {
            this.type = ImageType.OSHI;
        } else if ("GOODS".equals(dto.getType())) {
            this.type = ImageType.GOODS;
        } else if ("USER".equals(dto.getType())) {
            this.type = ImageType.USER;
        } else if ("SALES".equals(dto.getType())) {
            this.type = ImageType.SALES;
        } else {
            // 예외 처리 또는 기본값 설정
            throw new IllegalArgumentException("Invalid type: " + dto.getType());
        }
    }
}
