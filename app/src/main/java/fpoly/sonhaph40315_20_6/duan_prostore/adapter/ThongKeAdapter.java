package fpoly.sonhaph40315_20_6.duan_prostore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.ThongKe_Model;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.ViewHolder> {
    private ArrayList<ThongKe_Model> list;

    public ThongKeAdapter(ArrayList<ThongKe_Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ThongKeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thongke, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeAdapter.ViewHolder holder, int position) {
        ThongKe_Model item = list.get(position);

        holder.tvProductName.setText(item.getName());
        holder.tvSoldQuantity.setText(String.valueOf(item.getTotalQuantity()));

        String priceText = NumberFormat.getNumberInstance(new Locale("vi", "VN"))
                .format(item.getTotalRevenue()/item.getTotalQuantity()) + " VND";
        holder.tvPrice.setText("Giá: " + priceText);

        // Nếu có ảnh thì set, tạm dùng mặc định
        holder.imgProduct.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvProductName, tvSoldQuantity, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvSoldQuantity = itemView.findViewById(R.id.tvSoldQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
