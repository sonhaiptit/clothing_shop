package com.example.clothing_shop.service;

import com.example.clothing_shop.entity.DonHang;
import com.example.clothing_shop.repository.DonHangRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {
    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> getAllDonHang() {
        return donHangRepository.findAll();
    }

    public Optional<DonHang> getDonHangById(Long id) {
        return donHangRepository.findById(id);
    }

    @Transactional
    public DonHang saveDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Transactional
    public void updateTrangThai(Long maDH, String trangThai) {
        Optional<DonHang> donHangOpt = donHangRepository.findById(maDH);
        if (donHangOpt.isPresent()) {
            DonHang donHang = donHangOpt.get();
            donHang.setTrangThai(trangThai);
            donHangRepository.save(donHang);
        }
    }

    @Transactional
    public void deleteDonHang(Long id) {
        donHangRepository.deleteById(id);
    }

    public long countAllOrders() {
        return donHangRepository.count();
    }

    public List<DonHang> getDonHangByTrangThai(String trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }


    public long countByTrangThai(String trangThai) {
        return donHangRepository.countByTrangThai(trangThai);
    }
    public List<DonHang> getAllDonHangOrderByNgayTaoDesc() {
        return donHangRepository.findAllByOrderByNgayTaoDesc();
    }
    public long countByTrangThaiHoanThanh() {
        return donHangRepository.countByTrangThai("HOAN_THANH");
    }

    // Method tìm kiếm
    public List<DonHang> searchDonHang(String keyword) {
        return donHangRepository.findByHoTenContainingIgnoreCase(keyword);
    }
}
