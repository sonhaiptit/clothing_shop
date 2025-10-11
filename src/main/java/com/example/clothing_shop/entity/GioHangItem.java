package com.example.clothing_shop.entity;

import lombok.Data;

@Data
public class GioHangItem {
    private SanPham sanPham;
    private int soLuong;
    private double thanhTien;

    public GioHangItem(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.thanhTien = sanPham.getGia().doubleValue() * soLuong;
    }

    public void tangSoLuong(int soLuongThem) {
        this.soLuong += soLuongThem;
        this.thanhTien = sanPham.getGia().doubleValue() * this.soLuong;
    }

    public void capNhatSoLuong(int soLuongMoi) {
        this.soLuong = soLuongMoi;
        this.thanhTien = sanPham.getGia().doubleValue() * this.soLuong;
    }
}