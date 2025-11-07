package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.SanPham;
import com.example.clothing_shop.entity.GioHangItem;
import com.example.clothing_shop.service.SanPhamService;
import com.example.clothing_shop.service.DanhMucService;
import com.example.clothing_shop.service.GioHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private BaseController baseController;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        List<SanPham> sanPhamNoiBat = sanPhamService.getSanPhamNoiBat();
        model.addAttribute("sanPhamNoiBat", sanPhamNoiBat);
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());

//        // Thêm số lượng giỏ hàng vào model
//        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
//        model.addAttribute("tongSoLuongGioHang", gioHangService.tinhTongSoLuong(gioHang));
        baseController.addCommonAttributes(model, session);
        return "home";
    }


    @GetMapping("/san-pham")
    public String sanPham(Model model, HttpSession session) {
        model.addAttribute("sanPhamList", sanPhamService.getAllSanPham());
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());

//        // Thêm số lượng giỏ hàng vào model
//        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
//        model.addAttribute("tongSoLuongGioHang", gioHangService.tinhTongSoLuong(gioHang));
        baseController.addCommonAttributes(model, session);

        return "san-pham";
    }

    @GetMapping("/san-pham/{id}")
    public String chiTietSanPham(@PathVariable Long id, Model model, HttpSession session) {
        Optional<SanPham> sanPhamOpt = sanPhamService.getSanPhamById(id);

        if (sanPhamOpt.isPresent()) {
            SanPham sanPham = sanPhamOpt.get();
            model.addAttribute("sanPham", sanPham);

            // Lấy sản phẩm cùng danh mục (gợi ý)
            List<SanPham> sanPhamCungDanhMuc = sanPhamService.getSanPhamByDanhMuc(sanPham.getDanhMuc().getMaDM())
                    .stream()
                    .filter(sp -> !sp.getMaSP().equals(id))
                    .limit(4)
                    .collect(Collectors.toList());
            model.addAttribute("sanPhamCungDanhMuc", sanPhamCungDanhMuc);

//            // Thêm số lượng giỏ hàng vào model
//            List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
//            model.addAttribute("tongSoLuongGioHang", gioHangService.tinhTongSoLuong(gioHang));
            baseController.addCommonAttributes(model, session);

            return "chi-tiet-san-pham";
        } else {
            return "redirect:/san-pham";
        }
    }

    @GetMapping("/tim-kiem")
    public String timKiem(@RequestParam String keyword, Model model, HttpSession session) {
        List<SanPham> ketQua = sanPhamService.searchSanPham(keyword);
        model.addAttribute("ketQua", ketQua);
        model.addAttribute("keyword", keyword);

        // Thêm số lượng giỏ hàng vào model
//        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
//        model.addAttribute("tongSoLuongGioHang", gioHangService.tinhTongSoLuong(gioHang));
        baseController.addCommonAttributes(model, session);


        return "tim-kiem";
    }

    @GetMapping("/danh-muc/{maDM}")
    public String sanPhamTheoDanhMuc(@PathVariable Long maDM, Model model, HttpSession session) {
        model.addAttribute("sanPhamList", sanPhamService.getSanPhamByDanhMuc(maDM));
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());

//        // Thêm số lượng giỏ hàng vào model
//        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
//        model.addAttribute("tongSoLuongGioHang", gioHangService.tinhTongSoLuong(gioHang));
        baseController.addCommonAttributes(model, session);

        return "san-pham";
    }

    @PostMapping("/them-vao-gio-hang")
    public String themVaoGioHang(@RequestParam Long productId,
                                 @RequestParam int soLuong,
                                 HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        gioHang = gioHangService.themVaoGioHang(gioHang, productId, soLuong);
        session.setAttribute("gioHang", gioHang);

        return "redirect:/san-pham/" + productId + "?success=true";
    }

}