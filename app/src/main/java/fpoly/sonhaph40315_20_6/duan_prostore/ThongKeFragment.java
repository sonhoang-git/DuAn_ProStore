package fpoly.sonhaph40315_20_6.duan_prostore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.adapter.ThongKeAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DonHang_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.ThongKe_Model;

public class ThongKeFragment extends Fragment {

    private TextView tvDateRange, tvRevenue, tvOrderCount;
    private TextView btnPrev, btnNext;
    private RecyclerView recyclerView;
    private ThongKeAdapter adapter;
    private DonHang_Dao donHangDao;

    // Quản lý ngày
    private Calendar startDate, endDate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        tvDateRange = view.findViewById(R.id.tvDateRange);
        tvRevenue = view.findViewById(R.id.tvRevenue);
        tvOrderCount = view.findViewById(R.id.tvOrderCount);
        btnPrev = view.findViewById(R.id.btnPrev);
        btnNext = view.findViewById(R.id.btnNext);
        recyclerView = view.findViewById(R.id.recyclerViewBestSeller);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        donHangDao = new DonHang_Dao(getContext());
        adapter = new ThongKeAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Khởi tạo ngày bắt đầu là cách đây 15 ngày, kết thúc là hôm nay
        endDate = Calendar.getInstance();
        startDate = (Calendar) endDate.clone();
        startDate.add(Calendar.DAY_OF_MONTH, -15);

        btnPrev.setOnClickListener(v -> {
            shiftDateRange(-15);
        });

        btnNext.setOnClickListener(v -> {
            shiftDateRange(15);
        });

        //loadThongKe();

        return view;
    }

    private void shiftDateRange(int days) {
        startDate.add(Calendar.DAY_OF_MONTH, days);
        endDate.add(Calendar.DAY_OF_MONTH, days);
        //loadThongKe();
    }

//    private void loadThongKe() {
//        // Hiển thị khoảng thời gian
//        String dateFormat = "%d/%d - %d/%d";
//        tvDateRange.setText(String.format(dateFormat,
//                startDate.get(Calendar.DAY_OF_MONTH), startDate.get(Calendar.MONTH) + 1,
//                endDate.get(Calendar.DAY_OF_MONTH), endDate.get(Calendar.MONTH) + 1));
//
//        // Lấy dữ liệu thống kê từ DAO theo khoảng ngày
//        ArrayList<ThongKe_Model> bestSellers = donHangDao.getSoldProducts(
//                startDate.getTimeInMillis(),
//                endDate.getTimeInMillis());
//
//        // Tính tổng doanh thu và tổng đơn
//        double totalRevenue = 0;
//        int totalOrders = 0;
//        for (ThongKeAdapter sp : bestSellers) {
//            totalRevenue += sp.getTotalRevenue();
//            totalOrders += sp.getTotalQuantity();
//        }
//
//        // Hiển thị doanh thu & số lượng đơn
//        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
//        tvRevenue.setText(nf.format(totalRevenue) + " VND");
//        tvOrderCount.setText(String.valueOf(totalOrders));
//
//        // Cập nhật danh sách bán chạy
//        adapter.list.clear();
//        adapter.list.addAll(bestSellers);
//        adapter.notifyDataSetChanged();
//    }
}
