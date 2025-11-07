package com.example.clothing_shop.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "NhanVien")
@PrimaryKeyJoinColumn(name = "maND")
public class NhanVien extends NguoiDung {


    @Column(precision = 10, scale = 2)
    private BigDecimal luong;

    public NhanVien() {
        super();
        this.setVaiTro("NHANVIEN");
    }

    public NhanVien(String tenDangNhap, String matKhau, BigDecimal luong) {
        super(tenDangNhap, matKhau);
        this.luong = luong;
        this.setVaiTro("NHANVIEN");
    }

    @PrePersist
    public void prePersist() {
        if (getVaiTro() == null) setVaiTro("NHANVIEN");
    }
}
