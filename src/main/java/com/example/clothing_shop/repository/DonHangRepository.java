package com.example.clothing_shop.repository;

import com.example.clothing_shop.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByTrangThai(String trangThai);
    long countByTrangThai(String trangThai);
    List<DonHang> findByEmail(String email);
    List<DonHang> findBySoDienThoai(String soDienThoai);
    List<DonHang> findAllByOrderByNgayTaoDesc();
    List<DonHang> findByHoTenContainingIgnoreCase(String hoTen);
}