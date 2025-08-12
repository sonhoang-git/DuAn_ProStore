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
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.IsDelivering_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;

public class IsDelivering_Fragment extends Fragment {

//ang giao đơn hàng


    private RecyclerView recyclerView_danggiao;
    private IsDelivering_Adapter dangGiaoDonHangAdapter;
    private ArrayList<DonHang_Model> trangThaiModelArrayList;
    private DonHang_Dao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inde_divery_, container, false);


        recyclerView_danggiao = view.findViewById(R.id.recyclerView_danggiao);
        recyclerView_danggiao.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = new DonHang_Dao(getContext());
        trangThaiModelArrayList = dao.getDonHangByStatus("Đang giao");

        if (trangThaiModelArrayList == null) trangThaiModelArrayList = new ArrayList<>();

        dangGiaoDonHangAdapter = new IsDelivering_Adapter(getContext(), trangThaiModelArrayList);
        recyclerView_danggiao.setAdapter(dangGiaoDonHangAdapter);

        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // reload dữ liệu mỗi khi fragment hiển thị lại
        if (dao == null) dao = new DonHang_Dao(getContext());
        ArrayList<DonHang_Model> fresh = dao.getDonHangByStatus("Đang giao");
        trangThaiModelArrayList.clear();
        if (fresh != null) trangThaiModelArrayList.addAll(fresh);
        if (dangGiaoDonHangAdapter != null) dangGiaoDonHangAdapter.notifyDataSetChanged();
    }
}