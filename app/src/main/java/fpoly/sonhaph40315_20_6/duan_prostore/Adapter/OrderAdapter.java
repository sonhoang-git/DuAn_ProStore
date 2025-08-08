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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> list;

    FragmentManager fragmentManager;

    public OrderAdapter(Context context, List<Order> list, FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhang, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = list.get(position);
        holder.tvTenKhachHang.setText("Khách: " + order.getTenKhachHang());
        holder.tvSanPham.setText("Sản phẩm: " + order.getSanPham());
        holder.tvTongTien.setText("Tổng tiền: " + order.getTongTien() + "đ");
        holder.tvNgayDat.setText("Ngày đặt: " + order.getNgayDat());
        holder.tvTrangThai.setText("Trạng thái: " + order.getTrangThai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhachHang, tvSanPham, tvTongTien, tvNgayDat, tvTrangThai;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvSanPham = itemView.findViewById(R.id.tvSanPham);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            tvNgayDat = itemView.findViewById(R.id.tvNgayDat);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
        }
    }
}

