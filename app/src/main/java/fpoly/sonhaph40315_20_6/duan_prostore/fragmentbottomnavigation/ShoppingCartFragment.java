package fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.CartAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.CartManager;
import fpoly.sonhaph40315_20_6.duan_prostore.DienThongTinActivity;
import fpoly.sonhaph40315_20_6.duan_prostore.MainActivity;
import fpoly.sonhaph40315_20_6.duan_prostore.Product;
import fpoly.sonhaph40315_20_6.duan_prostore.R;

public class ShoppingCartFragment extends Fragment implements CartAdapter.OnCartChangedListener {

    private RecyclerView rcvCart;
    private TextView tvTotal;
    private Button btnCheckout;
    private ImageButton btnBack;
    private CartAdapter adapter;
    private double total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        rcvCart = view.findViewById(R.id.rcvCart);
        tvTotal = view.findViewById(R.id.tvTotalPrice);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        btnBack = view.findViewById(R.id.btnBack);

        setupRecyclerView();
        updateTotal();

        btnCheckout.setOnClickListener(v -> {
            if (total > 0) {
                // Lấy danh sách sản phẩm hiện tại từ CartManager
                List<Product> cartItems = CartManager.getInstance().getCartItems();
                // Chuyển sang ArrayList để putExtra
                ArrayList<Product> cartArray = new ArrayList<>(cartItems);

                Intent intent = new Intent(getContext(), DienThongTinActivity.class);
                intent.putExtra("TOTAL_AMOUNT", total);
                intent.putExtra("CART_ITEMS", cartArray); // truyền toàn bộ giỏ hàng
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Giỏ hàng trống!", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void setupRecyclerView() {
        List<Product> cartItems = CartManager.getInstance().getCartItems();
        adapter = new CartAdapter(getContext(), cartItems, this);
        rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvCart.setAdapter(adapter);
    }

    private void updateTotal() {
        total = 0;
        for (Product item : CartManager.getInstance().getCartItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotal.setText(String.format("%,.0f VND", total));
    }

    @Override
    public void onCartUpdated() {
        updateTotal();
    }
}
