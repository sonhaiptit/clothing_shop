package com.example.clothing_shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maCTDH;

    @ManyToOne
    @JoinColumn(name = "maDH")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "maSP")
    private SanPham sanPham;

    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
}