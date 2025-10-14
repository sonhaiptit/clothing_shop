package com.example.clothing_shop.controller;

import com.example.clothing_shop.entity.SanPham;
import com.example.clothing_shop.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*") // Cho phép frontend truy cập
public class TestController {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    // Endpoint 1: Lấy tất cả sản phẩm
    @GetMapping("/sanpham")
    public List<SanPham> getAllSanPham() {
        List<SanPham> products = sanPhamRepository.findAll();
        System.out.println("=== DEBUG: Found " + products.size() + " products ===");
        return products;
    }

    // Endpoint 2: Đếm số lượng sản phẩm
    @GetMapping("/count")
    public String getCount() {
        long count = sanPhamRepository.count();
        return "Total products in database: " + count;
    }

    // Endpoint 3: Lấy sản phẩm theo ID
    @GetMapping("/sanpham/{id}")
    public Object getSanPhamById(@PathVariable Long id) {
        try {
            SanPham product = sanPhamRepository.findById(id).orElse(null);
            if (product != null) {
                return product;
            } else {
                return "Product with ID " + id + " not found";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Endpoint 4: Kiểm tra kết nối database
    @GetMapping("/health")
    public String healthCheck() {
        try {
            long count = sanPhamRepository.count();
            return "✅ Application is running! Database connection OK. Total products: " + count;
        } catch (Exception e) {
            return "❌ Database connection error: " + e.getMessage();
        }
    }
}