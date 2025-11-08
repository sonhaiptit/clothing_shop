package com.example.clothing_shop.repository;

import com.example.clothing_shop.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByDanhMucMaDM(Long maDM);
    List<SanPham> findByTenContainingIgnoreCase(String ten);

    @Query("SELECT s FROM SanPham s ORDER BY s.daBan DESC")
    List<SanPham> findTop8ByOrderByDaBanDesc();

    List<SanPham> findBySoLuongGreaterThan(Integer soLuong);

}