package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.dao.GioHang_Dao;

public class CartManager {
    private Context context;
    private static CartManager instance;
    private  GioHang_Dao gioHang_dao;

    private CartManager(Context context) {
        gioHang_dao = new GioHang_Dao(context);
//        this.context = context;
//        gioHang_dao = new GioHang_Dao(context);
//        cartItems = new ArrayList<>(gioHangDao.getAll_GioHang());
    }


    public static CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context.getApplicationContext());
        }
        return instance;
    }

//    public void addToCart(Product product) {
//        for (Product item : cartItems) {
//            if (item.getName().equals(product.getName()) && item.getSize().equals(product.getSize())) {
//                item.setQuantity(item.getQuantity() + 1);
//                return;
//            }
//        }
//        // Nếu chưa có sản phẩm giống (name + size), thêm mới vào giỏ
//        product.setQuantity(1);
//        cartItems.add(product);
//    }
//
//    public void removeFromCart(Product product) {
//        for (Product item : cartItems) {
//            if (item.getName().equals(product.getName()) && item.getSize().equals(product.getSize())) {
//                if (item.getQuantity() > 1) {
//                    item.setQuantity(item.getQuantity() - 1);
//                } else {
//                    cartItems.remove(item);
//                }
//                return;
//            }
//        }
//    }
//
//    public void clearCart() {
//        cartItems.clear();
//    }
//
//    public List<Product> getCartItems() {
//        return new ArrayList<>(cartItems);
//    }
//
//    public double getTotalAmount() {
//        double total = 0;
//        for (Product item : cartItems) {
//            total += Double.parseDouble(item.getPrice()) * item.getQuantity();
//
//        }
//        return total;
//    }

    public void add_GioHang(Product product) {
        gioHang_dao.add_GioHang(product);
    }

    public void remove_GioHang(Product product) {
        gioHang_dao.remove_GioHang(product);
    }

    public void Clear_GioHang() {
        gioHang_dao.clearCart();
    }

    public List <Product> getgiohang_Item(){
        return gioHang_dao.getAllGioHang();
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product item : getgiohang_Item()) {
            try {
                total += Double.parseDouble(item.getPrice().replace(",", "").replace(" VND", "")) * item.getQuantity();
            } catch (Exception ignored) {}
        }
        return total;
    }

    public void clearCart() {
        gioHang_dao.clearCart();
    }
}
