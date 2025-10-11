package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.GioHangItem;
import com.example.clothing_shop.service.GioHangService;
import com.example.clothing_shop.service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gio-hang")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping
    public String xemGioHang(HttpSession session, Model model) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new ArrayList<>();
        }

        model.addAttribute("gioHang", gioHang);
        model.addAttribute("tongTien", gioHangService.tinhTongTien(gioHang));
        model.addAttribute("tongSoLuong", gioHangService.tinhTongSoLuong(gioHang));

        return "gio-hang";
    }

    @PostMapping("/them")
    public String themVaoGioHang(@RequestParam Long productId,
                                 @RequestParam(defaultValue = "1") int soLuong,
                                 HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        gioHang = gioHangService.themVaoGioHang(gioHang, productId, soLuong);
        session.setAttribute("gioHang", gioHang);

        return "redirect:/gio-hang";
    }

    @PostMapping("/cap-nhat")
    public String capNhatSoLuong(@RequestParam Long productId,
                                 @RequestParam int soLuong,
                                 HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        gioHang = gioHangService.capNhatSoLuong(gioHang, productId, soLuong);
        session.setAttribute("gioHang", gioHang);

        return "redirect:/gio-hang";
    }

    @PostMapping("/xoa")
    public String xoaKhoiGioHang(@RequestParam Long productId,
                                 HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        gioHang = gioHangService.xoaKhoiGioHang(gioHang, productId);
        session.setAttribute("gioHang", gioHang);

        return "redirect:/gio-hang";
    }

    @PostMapping("/xoa-tat-ca")
    public String xoaTatCa(HttpSession session) {
        session.removeAttribute("gioHang");
        return "redirect:/gio-hang";
    }
    @GetMapping("/thanh-toan")
    public String chuyenDenThanhToan(HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.size() == 0) {
            return "redirect:/gio-hang";
        }

        return "redirect:/thanh-toan";
    }
}