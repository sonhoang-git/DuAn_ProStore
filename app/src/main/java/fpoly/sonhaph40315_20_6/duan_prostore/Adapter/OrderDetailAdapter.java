package fpoly.sonhaph40315_20_6.duan_prostore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.Model.OrderDetail;
import fpoly.sonhaph40315_20_6.duan_prostore.R;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private Context context;
    private List<OrderDetail> list;

    public OrderDetailAdapter(Context context, List<OrderDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail item = list.get(position);
        holder.tvTenSanPham.setText(item.getTenSanPham());
        holder.tvSoLuong.setText("x" + item.getSoLuong());

        NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
        holder.tvGia.setText(nf.format(item.getDonGia()) + " ₫");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvSoLuong, tvGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGia = itemView.findViewById(R.id.tvGia);
        }
    }
}
