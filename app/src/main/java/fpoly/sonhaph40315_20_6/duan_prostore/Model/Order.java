package fpoly.sonhaph40315_20_6.duan_prostore.Model;

public class Order {
    private int id;
    private String tenKhachHang;
    private String ngayDat;
    private String sanPham;
    private double tongTien;
    private String trangThai;

    public Order(int id, String tenKhachHang, String ngayDat, String sanPham, double tongTien, String trangThai) {
        this.id = id;
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.sanPham = sanPham;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    // Getter & Setter cho sanPham
    public String getSanPham() {
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        this.sanPham = sanPham;
    }

    // Các getter & setter còn lại
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenKhachHang() { return tenKhachHang; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }

    public String getNgayDat() { return ngayDat; }
    public void setNgayDat(String ngayDat) { this.ngayDat = ngayDat; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
