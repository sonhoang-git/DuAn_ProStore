package fpoly.sonhaph40315_20_6.duan_prostore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Adapter.OrderAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.Order;

public class DonHangFragment extends Fragment {

    private RecyclerView rcvDonHang;
    private OrderAdapter adapter;
    private List<Order> donHangList;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView icMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);

        // Ánh xạ view
        rcvDonHang = view.findViewById(R.id.rcvDonHang);
        icMenu = view.findViewById(R.id.icMenu);

        // Lấy Drawer từ Activity chứa fragment
        drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        navigationView = requireActivity().findViewById(R.id.nav_view);

        // Bắt sự kiện mở menu
        icMenu.setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Khởi tạo danh sách đơn hàng và adapter
        donHangList = new ArrayList<>();
        fakeData(); // Thêm dữ liệu mẫu

        adapter = new OrderAdapter(getContext(), donHangList, getParentFragmentManager());
        rcvDonHang.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvDonHang.setAdapter(adapter);

        return view;
    }

    private void fakeData() {
        donHangList.add(new Order(1, "Nguyễn Văn A", "2025-07-25", "Áo thun nam", 129000, "Đã giao"));
        donHangList.add(new Order(2, "Trần Thị B", "2025-07-26", "Áo khoác nữ", 245000, "Đang giao"));
        donHangList.add(new Order(3, "Lê Văn C", "2025-07-27", "Quần jean nam", 350000, "Chưa giao"));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        }
    }
}
