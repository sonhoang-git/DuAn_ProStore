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
import fpoly.sonhaph40315_20_6.duan_prostore.Dao.OrderDao;
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
        drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        navigationView = requireActivity().findViewById(R.id.nav_view);

        // Bắt sự kiện mở menu
        icMenu.setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Lấy dữ liệu từ database
        OrderDao orderDao = new OrderDao(getContext());
        donHangList = orderDao.getAll();

        // Setup RecyclerView
        adapter = new OrderAdapter(getContext(), donHangList, getParentFragmentManager());
        rcvDonHang.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvDonHang.setAdapter(adapter);

        return view;
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
