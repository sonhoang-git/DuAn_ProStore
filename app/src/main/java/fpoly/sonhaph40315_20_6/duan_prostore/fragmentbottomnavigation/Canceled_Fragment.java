package fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.Canceled_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.Delivered_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.IsDelivering_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;


public class Canceled_Fragment extends Fragment {
    // đã huỷ đơn hàng
    private RecyclerView recyclerView_dahuy;
    private Canceled_Adapter daHuyDonHangAdapter;
    private ArrayList<DonHang_Model> trangThaiModelArrayList;
    private DonHang_Dao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canceled_, container, false);
        recyclerView_dahuy = view.findViewById(R.id.recyclerView_dahuy);
        recyclerView_dahuy.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = new DonHang_Dao(getContext());
        trangThaiModelArrayList = dao.getDonHangByStatus("Đã giao");
        if (trangThaiModelArrayList == null) trangThaiModelArrayList = new ArrayList<>();
        daHuyDonHangAdapter = new Canceled_Adapter(getContext(),trangThaiModelArrayList);
        recyclerView_dahuy.setAdapter(daHuyDonHangAdapter);


        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (dao == null) dao = new DonHang_Dao(getContext());
        ArrayList<DonHang_Model> fresh = dao.getDonHangByStatus("Đã giao");
        trangThaiModelArrayList.clear();
        if (fresh != null) trangThaiModelArrayList.addAll(fresh);
        if (daHuyDonHangAdapter != null) daHuyDonHangAdapter.notifyDataSetChanged();
    }
}