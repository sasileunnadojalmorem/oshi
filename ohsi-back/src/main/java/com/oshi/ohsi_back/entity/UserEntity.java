package com.oshi.ohsi_back.entity;

import javax.persistence.*;

import com.oshi.ohsi_back.dto.request.auth.SignUpRequestDto;

import lombok.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id"  )
    private int userid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true ,name = "profile_image_id")
    private String profileImageUrl;
    
    
    // SignUpRequestDto를 사용하는 생성자
    public UserEntity(SignUpRequestDto dto) {

        this.username = dto.getUsername();
        this.email = dto.getEmail(); 
        this.password = dto.getPassword();
    }
}
