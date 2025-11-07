package com.example.clothing_shop.repository;

import com.example.clothing_shop.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    Optional<NguoiDung> findByTenDangNhapOrEmail(String tenDangNhap, String email);
    Optional<NguoiDung> findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    Optional<NguoiDung> findByEmail(String email);
    boolean existsByTenDangNhap(String tenDangNhap);
    boolean existsByEmail(String email);
}