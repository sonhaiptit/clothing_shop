package com.example.clothing_shop.service;

import com.example.clothing_shop.repository.NguoiDungRepository;
import com.example.clothing_shop.entity.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public Optional<NguoiDung> findByTenDangNhapOrEmail(String username) {
        return nguoiDungRepository.findByTenDangNhapOrEmail(username, username);
    }

    public List<NguoiDung> getAllUsers() {
        return nguoiDungRepository.findAll();
    }

    public long countAllUsers() {
        return nguoiDungRepository.count();
    }

    public void updateUserRole(Long userId, String newRole) {
        Optional<NguoiDung> userOpt = nguoiDungRepository.findById(userId);
        if (userOpt.isPresent()) {
            NguoiDung user = userOpt.get();
            user.setVaiTro(newRole);
            nguoiDungRepository.save(user);
        }
    }
}
