package com.example.clothing_shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maND;

    @Column(unique = true, nullable = false)
    private String tenDangNhap;
    private String email;

    @Column(nullable = false)
    private String matKhau;

    private String ten;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String soDienThoai;

    private LocalDateTime ngayTao = LocalDateTime.now();
    @Column(length = 20)
    private String vaiTro;
    public NguoiDung() {
        this.ngayTao = LocalDateTime.now();
    }
    public NguoiDung(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

}
