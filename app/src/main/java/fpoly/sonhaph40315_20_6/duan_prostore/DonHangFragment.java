package fpoly.sonhaph40315_20_6.duan_prostore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.stream.IntStream;

import fpoly.sonhaph40315_20_6.duan_prostore.adapter.DonHangAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;

public class DonHangFragment extends Fragment {

    private RecyclerView recyclerView;
    private DonHangAdapter adapter;
    private DonHang_Dao donHangDao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewDonHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        donHangDao = new DonHang_Dao(getContext());

        adapter = new DonHangAdapter(new ArrayList<>(), donHang -> {
            // Xử lý click item nếu cần
        });

        // Gán listener cho icon sửa trạng thái
        adapter.setOnEditStatusClickListener(donHang -> showEditStatusDialog(donHang));

        recyclerView.setAdapter(adapter);

        loadDonHangData();

        return view;
    }

    private void loadDonHangData() {
        ArrayList<DonHang_Model> list = donHangDao.getAllDonHang();
        adapter.setData(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDonHangData();
    }

    private void showEditStatusDialog(DonHang_Model donHang) {
        String[] trangThaiOptions = {"Chờ xác nhận", "Đã thanh toán", "Đang giao", "Đã hủy", "Đã xác nhận", "Đã giao", "Chưa thanh toán"};

        // Mảng boolean đánh dấu trạng thái nào đang được chọn dựa vào chuỗi status hiện tại
        boolean[] checkedItems = new boolean[trangThaiOptions.length];
        String currentStatus = donHang.getStatus() != null ? donHang.getStatus() : "";

        for (int i = 0; i < trangThaiOptions.length; i++) {
            // Nếu status hiện tại chứa trạng thái nào thì check tương ứng
            checkedItems[i] = currentStatus.contains(trangThaiOptions[i]);
        }

        final int donHangId = donHang.getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Chỉnh sửa trạng thái")
                .setMultiChoiceItems(trangThaiOptions, checkedItems, (dialog, which, isChecked) -> {
                    // Cập nhật trạng thái checkbox khi user tick / bỏ tick
                    checkedItems[which] = isChecked;
                })
                .setPositiveButton("Lưu", (dialog, which) -> {
                    // Gom các trạng thái được chọn thành 1 chuỗi, phân tách bằng dấu phẩy
                    StringBuilder newStatusBuilder = new StringBuilder();
                    for (int i = 0; i < trangThaiOptions.length; i++) {
                        if (checkedItems[i]) {
                            if (newStatusBuilder.length() > 0) newStatusBuilder.append(", ");
                            newStatusBuilder.append(trangThaiOptions[i]);
                        }
                    }
                    String newStatus = newStatusBuilder.toString();

                    // Cập nhật đối tượng và database
                    donHang.setStatus(newStatus);
                    int updatedRows = donHangDao.updateStatus(donHangId, newStatus);

                    if (updatedRows > 0) {
                        loadDonHangData();
                    }
                    dialog.dismiss();
                })
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .show();
    }


}
