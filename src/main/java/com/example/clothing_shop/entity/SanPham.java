package com.example.clothing_shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSP;

    @ManyToOne
    @JoinColumn(name = "maDM")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "maTH")
    private ThuongHieu thuongHieu;

    private String ten;
    private String moTa;
    private BigDecimal gia;
    private Integer soLuong;
    private String kichCo;
    private String mauSac;
    private String chatLieu;
    private String hinhAnh;
    private Integer daBan = 0;
}