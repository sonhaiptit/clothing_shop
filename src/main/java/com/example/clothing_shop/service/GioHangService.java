package com.example.clothing_shop.service;

import com.example.clothing_shop.entity.GioHangItem;
import com.example.clothing_shop.entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangService {

    @Autowired
    private SanPhamService sanPhamService;

    public List<GioHangItem> themVaoGioHang(List<GioHangItem> gioHang, Long productId, int soLuong) {
        if (gioHang == null) {
            gioHang = new ArrayList<>();
        }

        Optional<SanPham> sanPhamOpt = sanPhamService.getSanPhamById(productId);
        if (sanPhamOpt.isPresent()) {
            SanPham sanPham = sanPhamOpt.get();

            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            Optional<GioHangItem> existingItem = gioHang.stream()
                    .filter(item -> item.getSanPham().getMaSP().equals(productId))
                    .findFirst();

            if (existingItem.isPresent()) {
                // Nếu đã có, tăng số lượng
                GioHangItem item = existingItem.get();
                item.tangSoLuong(soLuong);
            } else {
                // Nếu chưa có, thêm mới
                gioHang.add(new GioHangItem(sanPham, soLuong));
            }
        }

        return gioHang;
    }

    public List<GioHangItem> capNhatSoLuong(List<GioHangItem> gioHang, Long productId, int soLuong) {
        if (gioHang != null) {
            gioHang.stream()
                    .filter(item -> item.getSanPham().getMaSP().equals(productId))
                    .findFirst()
                    .ifPresent(item -> item.capNhatSoLuong(soLuong));
        }
        return gioHang;
    }

    public List<GioHangItem> xoaKhoiGioHang(List<GioHangItem> gioHang, Long productId) {
        if (gioHang != null) {
            gioHang.removeIf(item -> item.getSanPham().getMaSP().equals(productId));
        }
        return gioHang;
    }

    public double tinhTongTien(List<GioHangItem> gioHang) {
        if (gioHang == null) return 0;
        return gioHang.stream().mapToDouble(GioHangItem::getThanhTien).sum();
    }

    public int tinhTongSoLuong(List<GioHangItem> gioHang) {
        if (gioHang == null) return 0;
        return gioHang.stream().mapToInt(GioHangItem::getSoLuong).sum();
    }
}