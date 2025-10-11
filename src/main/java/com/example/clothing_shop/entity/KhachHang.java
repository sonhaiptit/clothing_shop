package com.example.clothing_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKH;

    @OneToOne
    @JoinColumn(name = "maND")
    private NguoiDung nguoiDung;

    private Integer diemTichLuy = 0;
}