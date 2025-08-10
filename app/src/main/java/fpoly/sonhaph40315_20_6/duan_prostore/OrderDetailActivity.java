package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView tvProductName, tvProductPrice;
    private TextView tvAddress, tvReceiverInfo, tvTotalAmount;
    private RadioButton rbCOD, rbPayment;
    private Button btnThanhToan;
    private ImageButton btnBack;

    private Product product; // Lưu sản phẩm đang chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Ánh xạ view
        btnBack = findViewById(R.id.btnBack);
        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvAddress = findViewById(R.id.tvAddress);
        tvReceiverInfo = findViewById(R.id.tvReceiverInfo);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        rbPayment = findViewById(R.id.rbPayment);
        btnThanhToan = findViewById(R.id.btnThanhToan);

        // Nhận dữ liệu từ ProductDetailActivity
        product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            imgProduct.setImageResource(product.getImageResId());
            tvProductName.setText(product.getName());
            tvProductPrice.setText(product.getPrice());
            tvTotalAmount.setText("Tổng tiền hàng: " + product.getPrice());
        } else {
            Toast.makeText(this, "Không có sản phẩm!", Toast.LENGTH_SHORT).show();
        }

        // Set thông tin cố định
        tvAddress.setText("Xóm 1, Xã Giao Hà, Giao Thủy, Nam Định");
        tvReceiverInfo.setText("Họ tên: Nguyễn Văn A\nSĐT: 0988468334\nEmail: hoangson23045@gmail.com");

        btnBack.setOnClickListener(v -> finish());

        // Nút Thanh toán → sang PaymentActivity
        btnThanhToan.setOnClickListener(v -> {
            if (product != null) {
                Intent intent = new Intent(OrderDetailActivity.this, PaymentActivity.class);
                intent.putExtra("product", product);

                // Parse giá từ String sang double (loại bỏ "VND" nếu có)
                String priceStr = product.getPrice().replace(" VND", "").replace(",", "").trim();
                double totalAmount = 0;
                try {
                    totalAmount = Double.parseDouble(priceStr) * product.getQuantity();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                intent.putExtra("TOTAL_AMOUNT", totalAmount);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Không có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
