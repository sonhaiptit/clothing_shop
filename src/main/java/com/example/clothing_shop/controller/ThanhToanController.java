package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.DonHang;
import com.example.clothing_shop.entity.GioHangItem;
import com.example.clothing_shop.service.ThanhToanService;
import com.example.clothing_shop.service.GioHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/thanh-toan")
public class ThanhToanController {

    @Autowired
    private ThanhToanService thanhToanService;

    @Autowired
    private GioHangService gioHangService;

    @GetMapping
    public String hienThiTrangThanhToan(HttpSession session, Model model) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.size() == 0) {
            return "redirect:/gio-hang";
        }

        model.addAttribute("gioHang", gioHang);
        model.addAttribute("tongTien", gioHangService.tinhTongTien(gioHang));
        model.addAttribute("donHang", new DonHang());

        return "thanh-toan";
    }

    @PostMapping("/xac-nhan")
    public String xacNhanDonHang(@ModelAttribute DonHang donHang,
                                 HttpSession session,
                                 Model model) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.size() == 0) {
            return "redirect:/gio-hang";
        }

        try {
            DonHang donHangDaTao = thanhToanService.taoDonHang(donHang, gioHang);

            // Xóa giỏ hàng sau khi thanh toán thành công
            session.removeAttribute("gioHang");

            model.addAttribute("donHang", donHangDaTao);
            return "thanh-toan-thanh-cong";

        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi đặt hàng. Vui lòng thử lại!");
            model.addAttribute("gioHang", gioHang);
            model.addAttribute("tongTien", gioHangService.tinhTongTien(gioHang));
            return "thanh-toan";
        }
    }
}