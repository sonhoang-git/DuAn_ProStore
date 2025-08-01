package fpoly.sonhaph40315_20_6.duan_prostore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.OrderDetailFragment;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.Order;
import fpoly.sonhaph40315_20_6.duan_prostore.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<Order> list;
    private FragmentManager fragmentManager;

    public OrderAdapter(Context context, List<Order> list, FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order donHang = list.get(position);

        holder.tvMaDon.setText("Mã đơn: #" + donHang.getId());
        holder.tvSanPham.setText(donHang.getSanPham());

        // Format tiền tệ Việt Nam
        NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        holder.tvTongTien.setText(vnFormat.format(donHang.getTongTien()) + " VNĐ");

        holder.tvNgay.setText("Ngày đặt: " + donHang.getNgayDat());
        holder.tvTrangThai.setText(donHang.getTrangThai());

        // Đổi màu trạng thái đơn hàng
        switch (donHang.getTrangThai().toLowerCase()) {
            case "đã giao":
                holder.tvTrangThai.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
                break;
            case "đang giao":
                holder.tvTrangThai.setTextColor(ContextCompat.getColor(context, R.color.dang_giao));
                break;
            default:
                holder.tvTrangThai.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
                break;
        }

        // 👇 Bắt sự kiện click để chuyển sang Fragment chi tiết đơn hàng
        holder.itemView.setOnClickListener(v -> {
            Fragment fragment = OrderDetailFragment.newInstance(donHang.getId());
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDon, tvSanPham, tvTongTien, tvNgay, tvTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDon = itemView.findViewById(R.id.tvMaDon);
            tvSanPham = itemView.findViewById(R.id.tvSanPham);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
        }
    }
}
