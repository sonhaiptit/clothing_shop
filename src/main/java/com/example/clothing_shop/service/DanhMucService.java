package com.example.clothing_shop.service;

import com.example.clothing_shop.entity.DanhMuc;
import com.example.clothing_shop.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;

    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }

    public Optional<DanhMuc> getDanhMucById(Long id) {
        return danhMucRepository.findById(id);
    }
    public long countAllCategories() {
        return danhMucRepository.count();
    }

    public void saveDanhMuc(DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
    }

    public void deleteDanhMuc(Long id) {
        danhMucRepository.deleteById(id);
    }
}