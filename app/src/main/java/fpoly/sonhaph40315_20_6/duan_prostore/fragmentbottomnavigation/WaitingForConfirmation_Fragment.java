package fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.WaitingForConfirmation_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.useractivity.OderStatus_Activity;


public class WaitingForConfirmation_Fragment extends Fragment {


    //Chờ xác nhận fragment

    private RecyclerView recyclerView_choxacnhan;

    private WaitingForConfirmation_Adapter choXacNhanDonHangAdapter;
    private ArrayList<DonHang_Model> trangThaiModelArrayList;
    private DonHang_Dao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waiting_for_confirmation_, container, false);
        recyclerView_choxacnhan = view.findViewById(R.id.recyclerView_choxacnhan);
        dao = new DonHang_Dao(getContext());
        trangThaiModelArrayList = dao.getDonHangChoXacNhan();

        choXacNhanDonHangAdapter = new WaitingForConfirmation_Adapter(getContext(),trangThaiModelArrayList);
        recyclerView_choxacnhan.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_choxacnhan.setAdapter(choXacNhanDonHangAdapter);


        return view;
    }

    // mới thêm
    @Override
    public void onResume() {
        super.onResume();
        // Reload dữ liệu mỗi khi fragment xuất hiện trở lại
        if (dao == null) dao = new DonHang_Dao(getContext());
        ArrayList<DonHang_Model> fresh = dao.getDonHangChoXacNhan();
        trangThaiModelArrayList.clear();
        trangThaiModelArrayList.addAll(fresh);
        if (choXacNhanDonHangAdapter != null) choXacNhanDonHangAdapter.notifyDataSetChanged();
    }
}