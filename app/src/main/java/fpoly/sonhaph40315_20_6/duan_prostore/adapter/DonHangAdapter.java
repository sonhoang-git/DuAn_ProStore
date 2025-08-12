package fpoly.sonhaph40315_20_6.duan_prostore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(DonHang_Model donHang);
    }

    public interface OnEditStatusClickListener {
        void onEditStatusClick(DonHang_Model donHang);
    }

    private ArrayList<DonHang_Model> list;
    private OnItemClickListener listener;
    private OnEditStatusClickListener editStatusListener;

    public DonHangAdapter(ArrayList<DonHang_Model> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    // Setter cho listener sửa trạng thái
    public void setOnEditStatusClickListener(OnEditStatusClickListener listener) {
        this.editStatusListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang_Model donHang = list.get(position);
        holder.tvName.setText(donHang.getName());
        holder.tvPrice.setText(donHang.getPrice());
        holder.tvSize.setText("Size: " + donHang.getSize());
        holder.tvQuantity.setText("SL: " + donHang.getQuantity());
        holder.tvStatus.setText(donHang.getStatus());
        holder.imgProduct.setImageResource(donHang.getImageresid());

        // Thông tin người đặt
        holder.tvFullName.setText("Họ tên: " + donHang.getFullName());
        holder.tvPhone.setText("SĐT: " + donHang.getPhone());
        holder.tvAddress.setText("Địa chỉ: " + donHang.getAddress());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(donHang);
            }
        });

        // Xử lý click icon sửa trạng thái
        holder.btnEditStatus.setOnClickListener(v -> {
            if (editStatusListener != null) {
                editStatusListener.onEditStatusClick(donHang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<DonHang_Model> newList) {
        this.list.clear();
        this.list.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice, tvSize, tvQuantity, tvStatus;
        TextView tvFullName, tvPhone, tvAddress;
        ImageView btnEditStatus;  // Icon sửa trạng thái

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvStatus = itemView.findViewById(R.id.tvStatus);

            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvAddress = itemView.findViewById(R.id.tvAddress);

            btnEditStatus = itemView.findViewById(R.id.btnEditStatus);
        }
    }
}
