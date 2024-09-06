package com.oshi.ohsi_back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sale_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
