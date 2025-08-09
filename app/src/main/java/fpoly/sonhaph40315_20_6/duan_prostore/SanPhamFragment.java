package fpoly.sonhaph40315_20_6.duan_prostore;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.dao.SanPham_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.database.SanPham_Database;

public class SanPhamFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;
    private SanPham_Dao sanPhamDao;
    private View rootView;
    private FloatingActionButton fabAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_san_pham, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewSanPham);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fabAdd = rootView.findViewById(R.id.fabAddProduct);

        sanPhamDao = new SanPham_Dao(new SanPham_Database(requireContext()), requireContext());

        fabAdd.setOnClickListener(v -> {
            DialogThemSanPham dialog = new DialogThemSanPham();
            dialog.setOnProductAddedListener(() -> refreshData());
            dialog.show(getChildFragmentManager(), "AddProductDialog");
        });

        loadData();

        return rootView;
    }

    private void loadData() {
        productList = sanPhamDao.getSanPham();

        adapter = new ProductAdapter(requireContext(), productList, true, new ProductAdapter.OnProductActionListener() {
            @Override
            public void onEdit(Product product) {
                DialogSuaSanPham dialog = DialogSuaSanPham.newInstance(product);
                dialog.setOnProductUpdatedListener(() -> refreshData());
                dialog.show(getChildFragmentManager(), "EditDialog");
            }

            @Override
            public void onDelete(Product product) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Xoá sản phẩm")
                        .setMessage("Bạn có chắc chắn muốn xoá sản phẩm này?")
                        .setPositiveButton("Xoá", (dialog, which) -> {
                            sanPhamDao.deleteSanPham(product.getId());
                            refreshData();
                        })
                        .setNegativeButton("Huỷ", null)
                        .show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void refreshData() {
        productList.clear();
        productList.addAll(sanPhamDao.getSanPham());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }
}

