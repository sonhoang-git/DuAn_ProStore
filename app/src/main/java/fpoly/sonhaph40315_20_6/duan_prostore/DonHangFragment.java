package fpoly.sonhaph40315_20_6.duan_prostore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.adapter.DonHangAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;

public class DonHangFragment extends Fragment {

    private RecyclerView recyclerView;
    private DonHangAdapter adapter;
    private DonHang_Dao donHangDao;

    public DonHangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewDonHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        donHangDao = new DonHang_Dao(getContext());
        loadDonHangData();

        return view;
    }

    private void loadDonHangData() {
        ArrayList<DonHang_Model> list = donHangDao.getDonHangChoXacNhan();
        adapter = new DonHangAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Cập nhật lại danh sách khi quay lại fragment
        loadDonHangData();
    }
}
