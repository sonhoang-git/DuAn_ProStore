package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;

public class OrderDetailActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView tvProductName, tvProductPrice;
    private TextView tvAddress, tvReceiverInfo, tvTotalAmount;
    private Button btnThanhToan;
    private ImageButton btnBack;

    private ArrayList<Product> cartItems;
    private DonHang_Model donHangThongTin; // Dùng DonHang_Model lưu thông tin người đặt
    private String paymentMethod;
    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        btnBack = findViewById(R.id.btnBack);
        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvAddress = findViewById(R.id.tvAddress);
        tvReceiverInfo = findViewById(R.id.tvReceiverInfo);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnThanhToan = findViewById(R.id.btnThanhToan);

        // Nhận dữ liệu
        cartItems = (ArrayList<Product>) getIntent().getSerializableExtra("CART_ITEMS");
        donHangThongTin = (DonHang_Model) getIntent().getSerializableExtra("donHangThongTin"); // Lấy đúng key và kiểu
        paymentMethod = getIntent().getStringExtra("paymentMethod");
        totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0);

        if (cartItems == null) cartItems = new ArrayList<>();

        // Hiển thị sản phẩm
        if (!cartItems.isEmpty()) {
            if (cartItems.size() == 1) {
                Product p = cartItems.get(0);
                try {
                    imgProduct.setImageResource(p.getImageResId());
                } catch (Exception e) { /* ignore */ }
                tvProductName.setText(p.getName());
                tvProductPrice.setText(String.format("%,.0f VND", p.getPrice()));
            } else {
                StringBuilder sb = new StringBuilder();
                for (Product p : cartItems) {
                    int qty = 1;
                    try { qty = p.getQuantity(); } catch (Exception ignored) {}
                    sb.append(p.getName())
                            .append(" x").append(qty)
                            .append(" - ").append(NumberFormat.getNumberInstance(Locale.US).format(p.getPrice())).append(" VND\n");
                }
                tvProductName.setText(sb.toString().trim());
                tvProductPrice.setText("");
            }

            if (totalAmount <= 0) {
                totalAmount = calcTotal(cartItems);
            }
            tvTotalAmount.setText(String.format("Tổng tiền hàng: %,.0f VND", totalAmount));
        } else {
            tvProductName.setText("Không có sản phẩm");
            tvTotalAmount.setText("Tổng tiền hàng: 0 VND");
        }

        // Hiển thị thông tin người nhận + phương thức thanh toán
        if (donHangThongTin != null) {
            tvAddress.setText(donHangThongTin.getAddress() != null ? donHangThongTin.getAddress() : "");
            String receiverInfo = "Họ tên: " + (donHangThongTin.getFullName() == null ? "" : donHangThongTin.getFullName())
                    + "\nSĐT: " + (donHangThongTin.getPhone() == null ? "" : donHangThongTin.getPhone())
                    + "\nThanh toán: " + (paymentMethod == null ? "" : paymentMethod);
            tvReceiverInfo.setText(receiverInfo);
        }

        btnBack.setOnClickListener(v -> finish());

        btnThanhToan.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Không có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                return;
            }

            DonHang_Dao donHangDao = new DonHang_Dao(OrderDetailActivity.this);
            boolean anySaved = false;

            // Xác định trạng thái thanh toán dựa vào paymentMethod
            String trangThaiThanhToan = (paymentMethod != null && !paymentMethod.isEmpty()) ? "Đã thanh toán" : "Chưa thanh toán";

            for (Product p : cartItems) {
                DonHang_Model dh = new DonHang_Model(
                        0,
                        p.getImageResId(),
                        p.getName(),
                        String.format("%,.0f VND", p.getPrice()),
                        p.getSize(),
                        p.getQuantity(),
                        trangThaiThanhToan,  // Gán trạng thái thanh toán vào đây
                        donHangThongTin != null ? donHangThongTin.getFullName() : "",
                        donHangThongTin != null ? donHangThongTin.getPhone() : "",
                        donHangThongTin != null ? donHangThongTin.getAddress() : ""
                );
                donHangDao.add_DonHang(dh);
                anySaved = true;
            }

            if (anySaved) {
                Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderDetailActivity.this, MainActivity.class);
                intent.putExtra("openFragment", "DonHangFragment");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Lưu đơn hàng thất bại", Toast.LENGTH_SHORT).show();
            }




        if (anySaved) {
                Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderDetailActivity.this, MainActivity.class);
                intent.putExtra("openFragment", "DonHangFragment");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Lưu đơn hàng thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double calcTotal(ArrayList<Product> list) {
        double s = 0;
        for (Product p : list) {
            int qty = 1;
            try { qty = p.getQuantity(); } catch (Exception ignored) {}
            s += p.getPrice() * qty;
        }
        return s;
    }
}
