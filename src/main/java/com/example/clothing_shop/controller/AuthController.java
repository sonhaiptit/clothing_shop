package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.NguoiDung;
import com.example.clothing_shop.service.NguoiDungService;
import com.example.clothing_shop.repository.NguoiDungRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @GetMapping("/dang-nhap")
    public String showLoginPage(Model model){
        return "dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String showRegisterPage(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "dang-ky";
    }

    @PostMapping("/dang-nhap")
    public String login(@RequestParam String tenDangNhap,
                        @RequestParam String matKhau,
                        HttpSession session,
                        Model model) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findByTenDangNhapAndMatKhau(tenDangNhap, matKhau);

        if (optionalNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optionalNguoiDung.get();
            session.setAttribute("nguoidung", nguoiDung);

            switch (nguoiDung.getVaiTro()) {
                case "ADMIN":
                    return "redirect:/admin/dashboard";
                case "NHANVIEN":
                    return "redirect:/nhan-vien";
                default:
                    return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            return "dang-nhap";
        }
    }
    @PostMapping("/dang-ky")
    public String registerUser(@ModelAttribute("nguoiDung") NguoiDung nguoiDung,
                               Model model) {

        if (nguoiDungRepository.findByTenDangNhap(nguoiDung.getTenDangNhap()).isPresent()) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "dang-ky";
        }

        if (nguoiDungRepository.findByEmail(nguoiDung.getEmail()).isPresent()) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "dang-ky";
        }
        nguoiDung.setVaiTro("KHACH_HANG");
        nguoiDungRepository.save(nguoiDung);

        model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "redirect:/dang-nhap";
    }
    @GetMapping("/dang-xuat")
    public String logout(HttpSession session) {
        session.removeAttribute("nguoidung");
        session.invalidate();
        return "redirect:/dang-nhap";
    }
}
