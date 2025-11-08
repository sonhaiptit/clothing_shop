package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.DanhMuc;
import com.example.clothing_shop.entity.NguoiDung;
import com.example.clothing_shop.entity.SanPham;
import com.example.clothing_shop.entity.ThuongHieu;
import com.example.clothing_shop.service.DanhMucService;
import com.example.clothing_shop.service.NguoiDungService;
import com.example.clothing_shop.service.SanPhamService;
import com.example.clothing_shop.service.ThuongHieuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private BaseController baseController;
    @Autowired
    private ThuongHieuService thuongHieuService;

    private boolean hasAdminPermission(HttpSession session){
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoidung");
        return nguoiDung != null && "ADMIN".equals(nguoiDung.getVaiTro());
    }

    // Tự động xác định activeMenu
    @ModelAttribute("activeMenu")
    public String getActiveMenu(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/dashboard")) return "dashboard";
        if (requestURI.contains("/nguoi-dung")) return "nguoi-dung";
        if (requestURI.contains("/san-pham")) return "san-pham";
        if (requestURI.contains("/danh-muc")) return "danh-muc";
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        if(!hasAdminPermission(session)){
            return "redirect:/";
        }

        model.addAttribute("tongNguoiDung", nguoiDungService.countAllUsers());
        model.addAttribute("tongSanPham", sanPhamService.countAllProducts());
        model.addAttribute("tongDanhMuc", danhMucService.countAllCategories());
        model.addAttribute("donHangHomNay", 12);

        return "admin/dashboard";
    }

    @GetMapping("/nguoi-dung")
    public String quanLiNguoiDung(HttpSession session, Model model){
        if(!hasAdminPermission(session)){
            return "redirect:/";
        }

        baseController.setupLayout(session, model, "Quản lý người dùng", "admin");
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.getAllUsers();
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);

        return "admin/nguoi-dung";
    }

    @PostMapping("/nguoi-dung/cap-nhat-vai-tro")
    public String capNhatVaiTro(@RequestParam Long userId,
                                @RequestParam String newRole,
                                HttpSession session) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        nguoiDungService.updateUserRole(userId, newRole);
        return "redirect:/admin/nguoi-dung?success=true";
    }

    @GetMapping("/san-pham")
    public String quanLySanPham(HttpSession session, Model model) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        baseController.setupLayout(session, model, "Quản lý sản phẩm", "admin");
        List<SanPham> danhSachSanPham = sanPhamService.getAllSanPham();
        List<DanhMuc> danhMucList = danhMucService.getAllDanhMuc();
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAllThuongHieu();

        long sanPhamDangBan = danhSachSanPham.stream()
                .filter(sp -> sp.getSoLuong() > 0)
                .count();
        long sanPhamHetHang = danhSachSanPham.size() - sanPhamDangBan;

        model.addAttribute("danhSachSanPham", danhSachSanPham);
        model.addAttribute("danhMucList", danhMucList);
        model.addAttribute("thuongHieuList", thuongHieuList);
        model.addAttribute("sanPhamMoi", new SanPham());
        model.addAttribute("sanPhamDangBan", sanPhamDangBan);
        model.addAttribute("sanPhamHetHang", sanPhamHetHang);

        return "admin/san-pham";
    }

    @PostMapping("/san-pham/them")
    public String themSanPham(@ModelAttribute SanPham sanPham, HttpSession session) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        sanPhamService.saveSanPham(sanPham);
        return "redirect:/admin/san-pham?success=true";
    }

    @GetMapping("/san-pham/sua/{id}")
    public String suaSanPhamForm(@PathVariable Long id, HttpSession session, Model model) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        baseController.setupLayout(session, model, "Sửa sản phẩm", "admin");

        Optional<SanPham> sanPhamOpt = sanPhamService.getSanPhamById(id);
        if (sanPhamOpt.isPresent()) {
            model.addAttribute("sanPham", sanPhamOpt.get());
            model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
            return "admin/sua-san-pham";
        }

        return "redirect:/admin/san-pham?error=notfound";
    }

    @PostMapping("/san-pham/cap-nhat")
    public String capNhatSanPham(@ModelAttribute SanPham sanPham, HttpSession session) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        sanPhamService.saveSanPham(sanPham);
        return "redirect:/admin/san-pham?success=true";
    }

    @PostMapping("/san-pham/xoa/{id}")
    public String xoaSanPham(@PathVariable Long id, HttpSession session) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        sanPhamService.deleteSanPham(id);
        return "redirect:/admin/san-pham?success=true";
    }

    @GetMapping("/danh-muc")
    public String quanLyDanhMuc(HttpSession session, Model model) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        baseController.setupLayout(session, model, "Quản lý danh mục", "admin");

        List<DanhMuc> danhSachDanhMuc = danhMucService.getAllDanhMuc();
        model.addAttribute("danhSachDanhMuc", danhSachDanhMuc);
        model.addAttribute("danhMucMoi", new DanhMuc());

        return "admin/danh-muc";
    }

    @PostMapping("/danh-muc/them")
    public String themDanhMuc(@ModelAttribute DanhMuc danhMuc, HttpSession session) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        danhMucService.saveDanhMuc(danhMuc);
        return "redirect:/admin/danh-muc?success=true";
    }

    @PostMapping("/danh-muc/xoa/{id}")
    public String xoaDanhMuc(@PathVariable Long id, HttpSession session) {
        if (!hasAdminPermission(session)) {
            return "redirect:/access-denied";
        }

        danhMucService.deleteDanhMuc(id);
        return "redirect:/admin/danh-muc?success=true";
    }

    @GetMapping("/access-denied")
    public String accessDenied(HttpSession session, Model model) {
        baseController.setupLayout(session, model, "Truy cập bị từ chối", "home");
        return "admin/access-denied";
    }

    @GetMapping("/home")
    public String goToHomePage() {
        return "redirect:/";
    }
}