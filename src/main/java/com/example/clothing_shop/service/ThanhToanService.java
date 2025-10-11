package com.example.clothing_shop.service;

import com.example.clothing_shop.entity.*;
import com.example.clothing_shop.repository.DonHangRepository;
import com.example.clothing_shop.repository.ChiTietDonHangRepository;
import com.example.clothing_shop.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ThanhToanService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Transactional
    public DonHang taoDonHang(DonHang donHang, List<GioHangItem> gioHang) {
        // Tính tổng tiền
        BigDecimal tongTien = gioHang.stream()
                .map(item -> item.getSanPham().getGia().multiply(BigDecimal.valueOf(item.getSoLuong())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        donHang.setTongTien(tongTien);
        donHang.setTrangThai("CHO_XAC_NHAN");

        // Lưu đơn hàng
        DonHang savedDonHang = donHangRepository.save(donHang);

        // Lưu chi tiết đơn hàng và cập nhật số lượng sản phẩm
        for (GioHangItem item : gioHang) {
            ChiTietDonHang chiTiet = new ChiTietDonHang();
            chiTiet.setDonHang(savedDonHang);
            chiTiet.setSanPham(item.getSanPham());
            chiTiet.setSoLuong(item.getSoLuong());
            chiTiet.setDonGia(item.getSanPham().getGia());
            chiTiet.setThanhTien(item.getSanPham().getGia().multiply(BigDecimal.valueOf(item.getSoLuong())));

            chiTietDonHangRepository.save(chiTiet);

            // Cập nhật số lượng sản phẩm
            SanPham sanPham = item.getSanPham();
            sanPham.setSoLuong(sanPham.getSoLuong() - item.getSoLuong());
            sanPham.setDaBan(sanPham.getDaBan() + item.getSoLuong());
            sanPhamRepository.save(sanPham);
        }

        return savedDonHang;
    }
}