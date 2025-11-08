package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.NguoiDung;
import com.example.clothing_shop.entity.GioHangItem;
import com.example.clothing_shop.service.GioHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.*;
@Controller
public class BaseController {

    @Autowired
    private GioHangService gioHangService;

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

    public void setupLayout(HttpSession session, Model model, String pageTitle, String activeMenu) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoidung");

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("currentUser", nguoiDung);
        model.addAttribute("isLoggedIn", nguoiDung != null);

        if (nguoiDung != null) {
            String vaiTro = nguoiDung.getVaiTro();
            model.addAttribute("userRole", vaiTro);
            model.addAttribute("isAdmin", "ADMIN".equals(vaiTro));
            model.addAttribute("isNhanVien", "NHANVIEN".equals(vaiTro));
            model.addAttribute("isKhachHang", "KHACH_HANG".equals(vaiTro) || "USER".equals(vaiTro));
        }
        model.addAttribute("menuItems", getMenuItems(
                nguoiDung != null ? nguoiDung.getVaiTro() : null,
                activeMenu
        ));

        model.addAttribute("tongSoLuongGioHang", getTongSoLuongGioHang(session));
    }

    private List<Map<String, Object>> getMenuItems(String role, String activeMenu) {
        List<Map<String, Object>> menuItems = new ArrayList<>();

        addMenuItem(menuItems, "Trang chủ", "/", "fas fa-home", "home".equals(activeMenu));
        addMenuItem(menuItems, "Sản phẩm", "/san-pham", "fas fa-tshirt", "products".equals(activeMenu));

        if ("ADMIN".equals(role)) {
            addMenuItem(menuItems, "Admin", "/admin", "fas fa-cogs", "admin".equals(activeMenu));
        }

        if (role != null) {
            addMenuItem(menuItems, "Giỏ hàng", "/gio-hang", "fas fa-shopping-cart", "cart".equals(activeMenu));

            if ("KHACH_HANG".equals(role)) {
                addMenuItem(menuItems, "Tài khoản", "/tai-khoan", "fas fa-user", "account".equals(activeMenu));
            }
        }

        return menuItems;
    }
    private void addMenuItem(List<Map<String, Object>> menuItems, String title, String url, String icon, boolean isActive) {
        Map<String, Object> menuItem = new HashMap<>();
        menuItem.put("title", title);
        menuItem.put("url", url);
        menuItem.put("icon", icon);
        menuItem.put("active", isActive);
        menuItems.add(menuItem);
    }
    private int getTongSoLuongGioHang(HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        return gioHangService.tinhTongSoLuong(gioHang);
    }
}