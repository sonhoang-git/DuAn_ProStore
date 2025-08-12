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
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.IsDelivering_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;


public class Canceled_Fragment extends Fragment {
    // đã huỷ đơn hàng
    private RecyclerView recyclerView_dahuy;
    private Canceled_Adapter daHuyDonHangAdapter;
    private ArrayList<DonHang_Model> trangThaiModelArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canceled_, container, false);
        recyclerView_dahuy = view.findViewById(R.id.recyclerView_dahuy);
        trangThaiModelArrayList = new ArrayList<>();
        trangThaiModelArrayList.add(new DonHang_Model( 1,  R.drawable.product_useravata_icon, "Aó trẻ em","100", "M",2,"Đa huy","Nguyễn Văn A","0123456789","Hà Nội"));
        trangThaiModelArrayList.add(new DonHang_Model( 2,  R.drawable.product_useravata_icon,"Aó trẻ em", "100", "M",2, "Đa huy","Nguyễn Văn A","0123456789","Hà Nội"));
        trangThaiModelArrayList.add(new DonHang_Model( 3,  R.drawable.product_useravata_icon, "Aó trẻ em","100", "M",2, "Đa huy","Nguyễn Văn A","0123456789","Hà Nội"));

        daHuyDonHangAdapter = new Canceled_Adapter(getContext(),trangThaiModelArrayList);
        recyclerView_dahuy.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_dahuy.setAdapter(daHuyDonHangAdapter);


        return view;
    }
}