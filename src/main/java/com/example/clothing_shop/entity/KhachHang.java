package com.example.clothing_shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "KhachHang")
public class KhachHang extends NguoiDung{
    private Integer diemTichLuy = 0;
}