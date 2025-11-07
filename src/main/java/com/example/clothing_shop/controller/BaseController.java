package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.NguoiDung;
import com.example.clothing_shop.entity.GioHangItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.*;
@Controller
public class BaseController {

    public void addCommonAttributes(Model model, HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoidung");

        model.addAttribute("currentUser", nguoiDung);
        model.addAttribute("isLoggedIn", nguoiDung != null);

        if (nguoiDung != null) {
            String vaiTro = nguoiDung.getVaiTro();
            model.addAttribute("isAdmin", "ADMIN".equals(vaiTro));
            model.addAttribute("isNhanVien", "NHANVIEN".equals(vaiTro));
        }

        // Tính tổng số lượng giỏ hàng
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        int tongSoLuong = gioHang != null ?
                gioHang.stream().mapToInt(GioHangItem::getSoLuong).sum() : 0;
        model.addAttribute("tongSoLuongGioHang", tongSoLuong);
    }
}