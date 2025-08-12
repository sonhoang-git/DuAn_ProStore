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
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.Delivered_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.WaitingForConfirmation_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;


public class Delivered_Fragment extends Fragment {


// đã giao đơn hàng

    private RecyclerView recyclerView_dagiao;

    private Delivered_Adapter daGiaoDonHangAdapter;
    private ArrayList<DonHang_Model> trangThaiModelArrayList;
    private DonHang_Dao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivered, container, false);
        recyclerView_dagiao = view.findViewById(R.id.recyclerView_dagiao);
        recyclerView_dagiao.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = new DonHang_Dao(getContext());
        trangThaiModelArrayList = dao.getDonHangByStatus("Đã giao");
        if (trangThaiModelArrayList == null) trangThaiModelArrayList = new ArrayList<>();
        daGiaoDonHangAdapter = new Delivered_Adapter(getContext(),trangThaiModelArrayList);
        recyclerView_dagiao.setAdapter(daGiaoDonHangAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dao == null) dao = new DonHang_Dao(getContext());
        ArrayList<DonHang_Model> fresh = dao.getDonHangByStatus("Đã giao");
        trangThaiModelArrayList.clear();
        if (fresh != null) trangThaiModelArrayList.addAll(fresh);
        if (daGiaoDonHangAdapter != null) daGiaoDonHangAdapter.notifyDataSetChanged();
    }
}