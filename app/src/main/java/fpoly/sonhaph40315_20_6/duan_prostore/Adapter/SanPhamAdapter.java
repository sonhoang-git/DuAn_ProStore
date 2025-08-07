package fpoly.sonhaph40315_20_6.duan_prostore.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Model.SanPham;
import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.SanPhamChiTietFragment;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    private final Context context;
    private final List<SanPham> productList;
    private final OnProductActionListener listener;

    public interface OnProductActionListener {
        void onEdit(SanPham product);
        void onDelete(SanPham product);
    }

    public SanPhamAdapter(Context context, List<SanPham> productList, OnProductActionListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham product = productList.get(position);

        holder.tvName.setText(product.getName());
        holder.tvQuantity.setText("Số lượng: " + product.getQuantity());
        holder.tvDate.setText("Ngày: " + product.getDate());

        if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
            Glide.with(context)
                    .load(Uri.parse(product.getImagePath()))
                    .placeholder(R.drawable.ic_product)
                    .into(holder.imgProduct);
        } else {
            holder.imgProduct.setImageResource(R.drawable.ic_product);
        }

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(product);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(product);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            SanPham clickedProduct = productList.get(holder.getAdapterPosition());

            Bundle bundle = new Bundle();
            bundle.putSerializable("sanpham", clickedProduct);

            SanPhamChiTietFragment fragment = new SanPhamChiTietFragment();
            fragment.setArguments(bundle);

            ((AppCompatActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) // Đảm bảo FrameLayout đúng ID
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, btnEdit, btnDelete;
        TextView tvName, tvQuantity, tvDate;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvDate = itemView.findViewById(R.id.tvDate);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
