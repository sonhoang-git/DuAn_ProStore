package fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.Product;
import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.adapter.ProductList_Adapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.SanPham_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.database.SanPham_Database;
import fpoly.sonhaph40315_20_6.duan_prostore.model.ProductList_Model;


public class ProductListFragment extends Fragment {

    private RecyclerView recyclerView_productList;
    private ProductList_Adapter productListAdapter;
    private ArrayList<Product> arrayList_ProductList;
    private SanPham_Database sanPham_database;
    private SanPham_Dao sanPham_dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView_productList = view.findViewById(R.id.recyclerView_productList);
        recyclerView_productList.setLayoutManager(new LinearLayoutManager(getContext()));

        sanPham_database = new SanPham_Database(requireContext());
        sanPham_dao = new SanPham_Dao(sanPham_database,requireContext());
        arrayList_ProductList = sanPham_dao.getSanPham();
        productListAdapter = new ProductList_Adapter(getContext(),arrayList_ProductList);
        recyclerView_productList.setAdapter(productListAdapter);
        return view;
    }
}