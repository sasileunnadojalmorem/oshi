package com.oshi.ohsi_back.entity;

import javax.persistence.*;
import lombok.*;
import java.util.List;
import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private int userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    private ImageEntity profileImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SaleEntity> sales;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<BaseGoodsEntity> goods;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserOshiEntity> userOshis;

    // DTO를 사용한 생성자
    public UserEntity(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.username = dto.getUsername();
        
        // ImageEntity 객체 생성 후 ID만 설정
        ImageEntity image = new ImageEntity();
        image.setId(dto.getProfileImageId());
        this.profileImage = image;
    }
}