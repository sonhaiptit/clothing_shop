package com.example.clothing_shop.repository;


import com.example.clothing_shop.entity.QuanTriVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuanTriVienRepository extends JpaRepository<QuanTriVien, Long>{

}
