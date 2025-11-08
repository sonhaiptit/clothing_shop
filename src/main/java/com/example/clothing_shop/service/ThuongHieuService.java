package com.example.clothing_shop.service;

import com.example.clothing_shop.entity.ThuongHieu;
import com.example.clothing_shop.repository.ThuongHieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuService {
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;
    public List<ThuongHieu> getAllThuongHieu() {
        return thuongHieuRepository.findAll();
    }
}
