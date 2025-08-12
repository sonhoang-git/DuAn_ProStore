package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.NguoiDung_Model;

public class DienThongTinActivity extends AppCompatActivity {

    private EditText edtFullname, edtPhone, edtAddress, edtEmail;
    private RadioGroup rgHinhThuc;
    private Button btnXacNhan;
    private ArrayList<Product> cartItems;
    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thong_tin);

        // Ánh xạ view (IDs trong layout phải khớp: edtFullname, edtPhone, edtAddress, edtEmail, rgHinhThuc, btnXacNhan)
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        rgHinhThuc = findViewById(R.id.rgHinhThuc);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        // Nhận giỏ hàng + tổng tiền từ ShoppingCartFragment
        totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0);
        cartItems = (ArrayList<Product>) getIntent().getSerializableExtra("CART_ITEMS");
        if (cartItems == null) cartItems = new ArrayList<>();

        // Nếu muốn hiển thị tổng tiền ở đây, bạn có thể cập nhật 1 TextView (không có trong hiện layout mặc định)
        // Ví dụ: tvTotalInForm.setText(String.format("%,.0f VND", totalAmount));

        btnXacNhan.setOnClickListener(v -> {
            if (!validateInput()) return;

            String fullname = edtFullname.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();

            int selectedId = rgHinhThuc.getCheckedRadioButtonId();
            String paymentMethod = "";
            if (selectedId != -1) {
                RadioButton rb = findViewById(selectedId);
                paymentMethod = rb.getText().toString();
            }

            // Tạo NguoiDung_Model (dùng constructor phù hợp)
            DonHang_Model donHangThongTin = new DonHang_Model();
            donHangThongTin.setFullName(fullname);
            donHangThongTin.setPhone(phone);
            donHangThongTin.setAddress(address);


            Intent intent = new Intent(DienThongTinActivity.this, OrderDetailActivity.class);
            intent.putExtra("donHangThongTin", donHangThongTin);
            intent.putExtra("CART_ITEMS", cartItems);
            intent.putExtra("paymentMethod", paymentMethod);
            intent.putExtra("TOTAL_AMOUNT", totalAmount);
            startActivity(intent);
        });
    }

    private boolean validateInput() {
        if (edtFullname.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtPhone.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtAddress.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
