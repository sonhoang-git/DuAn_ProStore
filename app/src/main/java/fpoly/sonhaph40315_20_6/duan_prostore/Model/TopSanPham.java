package fpoly.sonhaph40315_20_6.duan_prostore.Model;

public class TopSanPham {
    private String name;
    private int soLuong;

    public TopSanPham(String name, int soLuong) {
        this.name = name;
        this.soLuong = soLuong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
