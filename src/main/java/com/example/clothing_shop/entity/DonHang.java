package com.example.clothing_shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "DonHang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDH;

    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChi;

    private BigDecimal tongTien;
    private String phuongThucThanhToan;
    private String trangThai;

    private LocalDateTime ngayTao = LocalDateTime.now();

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> chiTietDonHang = new ArrayList<>();
}