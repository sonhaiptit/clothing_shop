package com.example.clothing_shop.service;

import com.example.clothing_shop.entity.SanPham;
import com.example.clothing_shop.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    public Optional<SanPham> getSanPhamById(Long id) {
        return sanPhamRepository.findById(id);
    }

    public List<SanPham> searchSanPham(String keyword) {
        return sanPhamRepository.findByTenContainingIgnoreCase(keyword);
    }

    public List<SanPham> getSanPhamByDanhMuc(Long maDM) {
        return sanPhamRepository.findByDanhMucMaDM(maDM);
    }

    public List<SanPham> getSanPhamNoiBat() {
        return sanPhamRepository.findTop8ByOrderByDaBanDesc();
    }
    public long countAllProducts() {
        return sanPhamRepository.count();
    }

    public void saveSanPham(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    public void deleteSanPham(Long id) {
        sanPhamRepository.deleteById(id);
    }

}