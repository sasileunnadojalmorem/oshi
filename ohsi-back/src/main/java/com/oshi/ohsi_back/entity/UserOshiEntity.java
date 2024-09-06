package com.oshi.ohsi_back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_oshis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOshiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oshi_id", nullable = false)
    private OshiEntity oshi;

    // DTO 기반 생성자
    public UserOshiEntity(int user_id, int oshi_id) {
        this.user = new UserEntity();
        this.user.setUserId(user_id);

        this.oshi = new OshiEntity();
        this.oshi.setOshiId(oshi_id);
    }
}