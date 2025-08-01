package fpoly.sonhaph40315_20_6.duan_prostore.Model;

public class OrderDetail {
    private int id;
    private int orderId;
    private String tenSanPham;
    private int soLuong;
    private double donGia;

    public OrderDetail(int id, int orderId, String tenSanPham, int soLuong, double donGia) {
        this.id = id;
        this.orderId = orderId;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public String getTenSanPham() { return tenSanPham; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }

    public void setId(int id) { this.id = id; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
