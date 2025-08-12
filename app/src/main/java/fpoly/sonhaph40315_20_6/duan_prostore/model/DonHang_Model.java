package fpoly.sonhaph40315_20_6.duan_prostore.model;

import java.io.Serializable;

public class DonHang_Model implements Serializable {
    private int id;
    private int imageresid;
    private String name;
    private String price;
    private String size;
    private int quantity;
    private String status;
    private String fullName;
    private String phone;
    private String address;

    public DonHang_Model(){}

    public DonHang_Model(int id, int imageresid, String name, String price, String size, int quantity, String status, String fullName, String phone, String address) {
        this.id = id;
        this.imageresid = imageresid;
        this.name = name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.status = status;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageresid() {
        return imageresid;
    }

    public void setImageresid(int imageresid) {
        this.imageresid = imageresid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
