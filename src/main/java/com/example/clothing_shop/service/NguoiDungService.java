package com.example.clothing_shop.service;

import com.example.clothing_shop.repository.NguoiDungRepository;
import com.example.clothing_shop.entity.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public Optional<NguoiDung> findByTenDangNhapOrEmail(String username) {
        return nguoiDungRepository.findByTenDangNhapOrEmail(username, username);
    }
}
