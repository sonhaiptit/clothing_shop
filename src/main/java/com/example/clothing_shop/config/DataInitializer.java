package com.example.clothing_shop.config;

import com.example.clothing_shop.entity.*;
import com.example.clothing_shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== DATA INITIALIZER STARTED ===");

        // CHỈ tạo dữ liệu nếu database trống (KHÔNG xóa dữ liệu cũ)
        if (danhMucRepository.count() == 0) {
            System.out.println("Database is empty. Creating sample data...");

            // TẠO DATA MỚI
            initSampleData();

            System.out.println("=== DATA INITIALIZATION COMPLETED ===");
        } else {
            System.out.println("Database already has data. Skipping initialization.");
        }

        System.out.println("Categories: " + danhMucRepository.count());
        System.out.println("Brands: " + thuongHieuRepository.count());
        System.out.println("Products: " + sanPhamRepository.count());
    }

    private void initSampleData() {
        System.out.println("Creating sample data...");

        // Tạo danh mục
        List<DanhMuc> danhMucList = Arrays.asList(
                createDanhMuc("Áo Thun", "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500"),
                createDanhMuc("Quần Jeans", "https://images.unsplash.com/photo-1542272604-787c3835535d?w=500"),
                createDanhMuc("Áo Khoác", "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500"),
                createDanhMuc("Đầm/Váy", "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500"),
                createDanhMuc("Áo Sơ Mi", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=500"),
                createDanhMuc("Quần Short", "https://images.unsplash.com/photo-1594633312681-425c7b97ccd1?w=500"),
                createDanhMuc("Áo Len", "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=500")
        );
        danhMucRepository.saveAll(danhMucList);
        System.out.println("Created " + danhMucList.size() + " categories");

        // Tạo thương hiệu
        List<ThuongHieu> thuongHieuList = Arrays.asList(
                createThuongHieu("Nike", "https://logos-world.net/wp-content/uploads/2020/04/Nike-Logo.png"),
                createThuongHieu("Adidas", "https://logos-world.net/wp-content/uploads/2020/04/Adidas-Logo.png"),
                createThuongHieu("Zara", "https://logos-world.net/wp-content/uploads/2020/04/Zara-Logo.png"),
                createThuongHieu("Uniqlo", "https://logos-world.net/wp-content/uploads/2020/12/Uniqlo-Logo.png"),
                createThuongHieu("H&M", "https://logos-world.net/wp-content/uploads/2020/04/HM-Logo.png"),
                createThuongHieu("Gucci", "https://logos-world.net/wp-content/uploads/2020/04/Gucci-Logo.png"),
                createThuongHieu("Puma", "https://logos-world.net/wp-content/uploads/2020/04/Puma-Logo.png")
        );
        thuongHieuRepository.saveAll(thuongHieuList);
        System.out.println("Created " + thuongHieuList.size() + " brands");

        // Tạo 25 sản phẩm
        List<SanPham> sanPhamList = Arrays.asList(
                // Áo Thun (6 sản phẩm)
                createSanPham("Áo thun Nike Sportswear", "Áo thun thể thao cotton thoáng mát", 350000, 50, "S,M,L,XL", "Đen", "Cotton 100%", "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500", 120, danhMucList.get(0), thuongHieuList.get(0)),
                createSanPham("Áo thun Adidas Originals", "Áo thun cổ tròn in logo Adidas", 320000, 40, "M,L,XL", "Trắng", "Cotton", "https://images.unsplash.com/photo-1586790170083-2f9ceadc732d?w=500", 95, danhMucList.get(0), thuongHieuList.get(1)),
                createSanPham("Áo thun Puma Classic", "Áo thun basic Puma, chất liệu mềm mại", 280000, 35, "S,M,L", "Xám", "Cotton pha Spandex", "https://images.unsplash.com/photo-1618354691373-d851c5c3a990?w=500", 75, danhMucList.get(0), thuongHieuList.get(6)),
                createSanPham("Áo thun Uniqlo Airism", "Áo thun công nghệ Airism", 250000, 60, "S,M,L,XL,XXL", "Xanh navy", "Cotton Airism", "https://images.unsplash.com/photo-1503341455253-b2e723bb3dbb?w=500", 150, danhMucList.get(0), thuongHieuList.get(3)),
                createSanPham("Áo thun H&M Basic", "Áo thun basic giá rẻ", 150000, 100, "S,M,L,XL", "Trắng", "Cotton", "https://images.unsplash.com/photo-1523381210434-271e8be1f52b?w=500", 200, danhMucList.get(0), thuongHieuList.get(4)),
                createSanPham("Áo thun Gucci Logo", "Áo thun cao cấp in logo Gucci", 2500000, 10, "M,L", "Đen", "Cotton cao cấp", "https://images.unsplash.com/photo-1586790170083-2f9ceadc732d?w=500", 25, danhMucList.get(0), thuongHieuList.get(5)),

                // Quần Jeans (5 sản phẩm)
                createSanPham("Quần jeans Adidas Slim Fit", "Quần jeans form slim fit co giãn", 650000, 30, "28,29,30,31,32", "Xanh đậm", "Denim co giãn", "https://images.unsplash.com/photo-1542272604-787c3835535d?w=500", 85, danhMucList.get(1), thuongHieuList.get(1)),
                createSanPham("Quần jeans Nike Destroyed", "Quần jeans destroyed streetwear", 720000, 15, "29,30,31,32", "Xanh nhạt", "Denim", "https://images.unsplash.com/photo-1582418702059-97ebafb35d09?w=500", 35, danhMucList.get(1), thuongHieuList.get(0)),
                createSanPham("Quần jeans Zara Skinny", "Quần jeans form skinny, ôm chân", 550000, 25, "26,27,28,29", "Đen", "Denim skinny", "https://images.unsplash.com/photo-1473966968600-fa801b869a1a?w=500", 60, danhMucList.get(1), thuongHieuList.get(2)),
                createSanPham("Quần jeans H&M Regular", "Quần jeans form regular, thoải mái", 450000, 40, "30,31,32,33,34", "Xanh trung", "Denim regular", "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=500", 90, danhMucList.get(1), thuongHieuList.get(4)),
                createSanPham("Quần jeans Uniqlo Selvedge", "Quần jeans selvedge cao cấp", 850000, 20, "30,31,32", "Xanh indigo", "Denim selvedge", "https://images.unsplash.com/photo-1582552938357-32b906df40cb?w=500", 40, danhMucList.get(1), thuongHieuList.get(3)),

                // Áo Khoác (4 sản phẩm)
                createSanPham("Áo khoác Zara Basic", "Áo khoác basic phong cách Hàn Quốc", 850000, 20, "S,M,L", "Be", "Kaki", "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500", 45, danhMucList.get(2), thuongHieuList.get(2)),
                createSanPham("Áo khoác Nike Windrunner", "Áo khoác gió Nike, nhẹ gọn", 1200000, 15, "M,L,XL", "Đen", "Polyester", "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500", 30, danhMucList.get(2), thuongHieuList.get(0)),
                createSanPham("Áo khoác Adidas Essentials", "Áo khoác mùa đông Adidas", 950000, 18, "S,M,L,XL", "Xám", "Nỉ", "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?w=500", 55, danhMucList.get(2), thuongHieuList.get(1)),
                createSanPham("Áo khoác Uniqlo Ultra Light", "Áo khoác siêu nhẹ, gấp gọn được", 650000, 30, "S,M,L", "Xanh pastel", "Ultra light down", "https://images.unsplash.com/photo-1539533018447-63fcce2678e5?w=500", 70, danhMucList.get(2), thuongHieuList.get(3)),

                // Đầm/Váy (4 sản phẩm)
                createSanPham("Đầm body Uniqlo", "Đầm body dáng ôm, phù hợp đi làm", 550000, 25, "S,M,L", "Đỏ", "Vải tổng hợp", "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500", 60, danhMucList.get(3), thuongHieuList.get(3)),
                createSanPham("Váy liền Zara", "Váy liền thân dáng suông, thoải mái", 750000, 20, "S,M", "Hoa", "Vải voan", "https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?w=500", 45, danhMucList.get(3), thuongHieuList.get(2)),
                createSanPham("Đầm dạ hội Gucci", "Đầm dạ hội cao cấp, sang trọng", 8500000, 5, "S,M", "Đen", "Lụa", "https://images.unsplash.com/photo-1539008835657-9e8e9680c956?w=500", 8, danhMucList.get(3), thuongHieuList.get(5)),
                createSanPham("Váy công sở H&M", "Váy công sở lịch sự, form A-line", 450000, 35, "S,M,L", "Xanh dương", "Vải Kate", "https://images.unsplash.com/photo-1469334031218-e382a71b716b?w=500", 80, danhMucList.get(3), thuongHieuList.get(4)),

                // Áo Sơ Mi (3 sản phẩm)
                createSanPham("Áo sơ mi trắng Zara", "Áo sơ mi trắng basic, form regular", 550000, 30, "S,M,L,XL", "Trắng", "Cotton", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=500", 65, danhMucList.get(4), thuongHieuList.get(2)),
                createSanPham("Áo sơ mi kẻ sọc H&M", "Áo sơ mi kẻ sọc thanh lịch", 480000, 25, "M,L,XL", "Xanh trắng", "Cotton", "https://images.unsplash.com/photo-1621072156002-e2fccdc0b176?w=500", 50, danhMucList.get(4), thuongHieuList.get(4)),
                createSanPham("Áo sơ mi Uniqlo Premium", "Áo sơ mi cao cấp, không nhăn", 650000, 20, "S,M,L", "Xanh pastel", "Cotton premium", "https://images.unsplash.com/photo-1503341455253-b2e723bb3dbb?w=500", 40, danhMucList.get(4), thuongHieuList.get(3)),

                // Quần Short (3 sản phẩm)
                createSanPham("Quần short Nike Sport", "Quần short thể thao Nike, thoáng mát", 350000, 40, "M,L,XL", "Đen", "Polyester", "https://images.unsplash.com/photo-1594633312681-425c7b97ccd1?w=500", 85, danhMucList.get(5), thuongHieuList.get(0)),
                createSanPham("Quần short Adidas Originals", "Quần short casual Adidas", 320000, 35, "S,M,L", "Xám", "Cotton", "https://images.unsplash.com/photo-1506629905607-e48b0e67d879?w=500", 70, danhMucList.get(5), thuongHieuList.get(1)),
                createSanPham("Quần short Puma Basic", "Quần short basic Puma, nhiều màu", 280000, 45, "S,M,L,XL", "Navy", "Cotton", "https://images.unsplash.com/photo-1506629905607-e48b0e67d879?w=500", 95, danhMucList.get(5), thuongHieuList.get(6))
        );

        sanPhamRepository.saveAll(sanPhamList);
        System.out.println("Created " + sanPhamList.size() + " products");
    }

    private DanhMuc createDanhMuc(String ten, String hinhAnh) {
        DanhMuc dm = new DanhMuc();
        dm.setTen(ten);
        dm.setHinhAnh(hinhAnh);
        return dm;
    }

    private ThuongHieu createThuongHieu(String ten, String logo) {
        ThuongHieu th = new ThuongHieu();
        th.setTen(ten);
        th.setLogo(logo);
        return th;
    }

    private SanPham createSanPham(String ten, String moTa, int gia, int soLuong,
                                  String kichCo, String mauSac, String chatLieu,
                                  String hinhAnh, int daBan, DanhMuc danhMuc, ThuongHieu thuongHieu) {
        SanPham sp = new SanPham();
        sp.setTen(ten);
        sp.setMoTa(moTa);
        sp.setGia(new BigDecimal(gia));
        sp.setSoLuong(soLuong);
        sp.setKichCo(kichCo);
        sp.setMauSac(mauSac);
        sp.setChatLieu(chatLieu);
        sp.setHinhAnh(hinhAnh);
        sp.setDaBan(daBan);
        sp.setDanhMuc(danhMuc);
        sp.setThuongHieu(thuongHieu);
        return sp;
    }
}