package fpoly.sonhaph40315_20_6.duan_prostore.model;

public class DanhGia_Model {
    private int id;
    private String address;
    private String avata;

    private String namesanpham;
    private String userName;
    private int rating;
    private String noidung;
    private int price;


    public DanhGia_Model(int id, String address, String avata, String namesanpham, String userName, int rating, String noidung, int price) {
        this.id = id;
        this.address = address;
        this.avata = avata;
        this.namesanpham = namesanpham;
        this.userName = userName;
        this.rating = rating;
        this.noidung = noidung;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public String getNamesanpham() {
        return namesanpham;
    }

    public void setNamesanpham(String namesanpham) {
        this.namesanpham = namesanpham;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DanhGia_Model{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", namesanpham='" + namesanpham + '\'' +
                ", userName='" + userName + '\'' +
                ", rating=" + rating +
                ", noidung='" + noidung + '\'' +
                ", price=" + price +
                '}';
    }
}
