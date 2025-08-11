package fpoly.sonhaph40315_20_6.duan_prostore;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private int imageResId; // ảnh từ resource drawable
    private String imageUrl; // ảnh từ internet

    private String name;
    private double price;
    private int quantity;
    private String size;
    private String category;

    // Constructor cho ảnh từ resource
    public Product(int id, int imageResId, String name, double price, int quantity, String size, String category) {
        this.id = id;
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.category = category;
        this.imageUrl = null; // mặc định null
    }

    // Constructor cho ảnh từ URL
    public Product(int id, String imageUrl, String name, double price, int quantity, String size, String category) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.category = category;
        this.imageResId = 0; // mặc định 0
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
