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
    private String phuongThucThanhToan; // COD, BANKING
    private String trangThai; // CHO_XAC_NHAN, DANG_XU_LY, DANG_GIAO, DA_GIAO, DA_HUY

    private LocalDateTime ngayTao = LocalDateTime.now();

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> chiTietDonHang = new ArrayList<>();
}