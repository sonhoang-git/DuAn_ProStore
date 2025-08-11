package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Product;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rcvCart;
    private TextView tvTotal;
    private Button btnCheckout;
    private ImageView btnBack;

    private List<Product> cartItems;
    private CartAdapter cartAdapter;

    public static double total = 0; // để truyền sang ThanhToanActivity nếu cần

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ view
        rcvCart = findViewById(R.id.rcvCart);
        tvTotal = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnBack = findViewById(R.id.btnBack);

        setupRecyclerView();
        updateTotal();

//        btnCheckout.setOnClickListener(v -> {
//            if (total > 0) {
//                Intent intent = new Intent(CartActivity.this, ThanhToanActivity.class);
//                intent.putExtra("TOTAL_AMOUNT", total);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "Giỏ hàng trống!", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        cartItems = CartManager.getInstance().getCartItems(); // dữ liệu từ singleton
        cartAdapter = new CartAdapter(this, cartItems, this::updateTotal);
        rcvCart.setLayoutManager(new LinearLayoutManager(this));
        rcvCart.setAdapter(cartAdapter);
    }

    private void updateTotal() {
        total = 0;
        for (Product sp : cartItems) {
            total += sp.getPrice() * sp.getQuantity();
        }
        tvTotal.setText(String.format("%,.0f VND", total));
    }
}
