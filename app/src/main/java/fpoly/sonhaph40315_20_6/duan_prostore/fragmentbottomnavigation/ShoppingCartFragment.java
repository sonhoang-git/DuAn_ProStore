package fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.ShoppingCart_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.model.ShoppingCart_Model;


public class ShoppingCartFragment extends Fragment {

    RecyclerView recyclerView_shoppingCart;
    TextView txt_shoppingCart_tonggiatien;
    Button btn_shoppingCart_thanhtoan;
    ArrayList<ShoppingCart_Model> arrayList_ShoppingCart;
    ShoppingCart_Adapter shoppingCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        recyclerView_shoppingCart = view.findViewById(R.id.recyclerView_shoppingCart);
        txt_shoppingCart_tonggiatien = view.findViewById(R.id.txt_shoppingCart_tonggiatien);
        btn_shoppingCart_thanhtoan = view.findViewById(R.id.btn_shoppingCart_thanhtoan);

        arrayList_ShoppingCart = new ArrayList<>();
        arrayList_ShoppingCart.add(new ShoppingCart_Model( R.drawable.product_useravata_icon, 1, "Áo trẻ em", 100));
        arrayList_ShoppingCart.add(new ShoppingCart_Model(R.drawable.product_useravata_icon, 1, "Áo trẻ em", 100));
        arrayList_ShoppingCart.add(new ShoppingCart_Model(R.drawable.product_useravata_icon, 1, "Áo trẻ em", 100));
        arrayList_ShoppingCart.add(new ShoppingCart_Model(R.drawable.product_useravata_icon, 1, "Áo trẻ em", 100));
        arrayList_ShoppingCart.add(new ShoppingCart_Model(R.drawable.product_useravata_icon, 1, "Áo trẻ em", 100));


        shoppingCartAdapter = new ShoppingCart_Adapter(getContext(),arrayList_ShoppingCart);
        recyclerView_shoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_shoppingCart.setAdapter(shoppingCartAdapter);
        return view;
    }
}