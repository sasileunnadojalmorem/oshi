package com.oshi.ohsi_back.entity;

import javax.persistence.*;
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
    @Column(name = "id")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "oshi_id", nullable = false)
    private int oshi_id;
}
