package com.oshi.ohsi_back.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "user_oshis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOshiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "oshi_id", nullable = false)
    private int oshi_id;

    // 생성자를 public으로 수정
    public UserOshiEntity(int user_id, int oshi_id) {
        this.user_id = user_id;
        this.oshi_id = oshi_id;
    }
}
