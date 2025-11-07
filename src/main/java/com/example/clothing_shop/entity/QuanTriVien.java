package com.example.clothing_shop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "QuanTriVien")
@PrimaryKeyJoinColumn(name = "maND")
public class QuanTriVien extends NguoiDung {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public QuanTriVien() {
        super();
        this.setVaiTro("ADMIN");
    }

    public QuanTriVien(String tenDangNhap, String matKhau, String email) {
        super(tenDangNhap, matKhau);
        this.email = email;
        this.setVaiTro("ADMIN");
    }

    @PrePersist
    public void prePersist() {
        if (getVaiTro() == null) setVaiTro("ADMIN");
    }
}
