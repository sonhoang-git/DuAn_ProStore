package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;

public class PaymentActivity extends AppCompatActivity {

    private FrameLayout creditCardWrapper, bankAccountWrapper;
    private ImageView btnBack;
    private TextView tvTotalAmount;
    private Button btnPayment;
    private boolean isCreditCardSelected = true;
    private double totalAmount;
    private Product product; // Nhận sản phẩm từ OrderDetailActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initViews();
        receiveData();
        setupEventListeners();
        updatePaymentUI();
    }

    private void initViews() {
        creditCardWrapper = findViewById(R.id.creditCardWrapper);
        bankAccountWrapper = findViewById(R.id.bankAccountWrapper);
        btnBack = findViewById(R.id.btnBack);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnPayment = findViewById(R.id.btnPayment);
    }

    private void receiveData() {
        // Trường hợp 1: Nhận từ OrderDetailActivity
        totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0);
        product = (Product) getIntent().getSerializableExtra("product");

        // Nếu product null thì tính từ giỏ hàng
        if (product == null) {
            List<Product> cartItems = CartManager.getInstance().getCartItems();
            if (!cartItems.isEmpty()) {
                totalAmount = 0;
                for (Product p : cartItems) {
                    totalAmount += p.getPrice() * p.getQuantity();
                }
            }
        }

        tvTotalAmount.setText(String.format("Tổng\n%,.0f VND", totalAmount));
    }

    private void setupEventListeners() {
        creditCardWrapper.setOnClickListener(v -> {
            isCreditCardSelected = true;
            updatePaymentUI();
        });

        bankAccountWrapper.setOnClickListener(v -> {
            isCreditCardSelected = false;
            updatePaymentUI();
        });

        btnPayment.setOnClickListener(v -> processPayment());
        btnBack.setOnClickListener(v -> finish());
    }

    private void updatePaymentUI() {
        if (isCreditCardSelected) {
            creditCardWrapper.setBackgroundResource(R.drawable.bg_card_border);
            bankAccountWrapper.setBackgroundResource(R.drawable.bg_bank_button);
            btnPayment.setText("Thanh toán bằng thẻ tín dụng");
        } else {
            creditCardWrapper.setBackgroundResource(R.drawable.bg_bank_button);
            bankAccountWrapper.setBackgroundResource(R.drawable.bg_card_border);
            btnPayment.setText("Thanh toán bằng ngân hàng");
        }
    }

    private void processPayment() {
        DonHang_Dao donHangDao = new DonHang_Dao(this);

        if (product != null) {
            // Thanh toán 1 sản phẩm từ OrderDetailActivity
            DonHang_Model donHang = new DonHang_Model(
                    0,
                    product.getImageResId(),
                    product.getName(),
                    String.format("%,.0f VND", product.getPrice()), // Đổi double -> String
                    product.getSize(),
                    product.getQuantity(),
                    "Chờ xác nhận"
            );
            donHangDao.add_DonHang(donHang);

        } else {
            // Thanh toán toàn bộ giỏ hàng
            List<Product> cartItems = CartManager.getInstance().getCartItems();
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                return;
            }
            for (Product p : cartItems) {
                DonHang_Model donHang = new DonHang_Model(
                        0,
                        p.getImageResId(),
                        p.getName(),
                        String.format("%,.0f VND", p.getPrice()),
                        p.getSize(),
                        p.getQuantity(),
                        "Chờ xác nhận"
                );
                donHangDao.add_DonHang(donHang);
            }
            CartManager.getInstance().clearCart();
        }

        // Thông báo thanh toán thành công
        String message = isCreditCardSelected
                ? "Thanh toán bằng thẻ tín dụng thành công!"
                : "Thanh toán bằng ngân hàng thành công!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Quay về MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
