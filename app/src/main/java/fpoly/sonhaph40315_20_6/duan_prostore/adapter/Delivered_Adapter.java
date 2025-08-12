package fpoly.sonhaph40315_20_6.duan_prostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.useractivity.OrderInformation_Activity;
import fpoly.sonhaph40315_20_6.duan_prostore.useractivity.ProductReview_Activity;

public class Delivered_Adapter extends RecyclerView.Adapter<Delivered_Adapter.ViewHolder>{



    private final Context context;

    private final ArrayList<DonHang_Model> trangThaiModelArrayList;

    public Delivered_Adapter(Context context, ArrayList<DonHang_Model> trangThaiModelArrayList) {
        this.context = context;
        this.trangThaiModelArrayList = trangThaiModelArrayList;
    }

    @NonNull
    @Override
    public Delivered_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_delivered_fragment, parent, false);
        return new Delivered_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Delivered_Adapter.ViewHolder holder, int position) {
        DonHang_Model item = trangThaiModelArrayList.get(position);
        Glide.with(context)
                .load(item.getImageresid())  // nếu là resource ID: int, Glide vẫn hỗ trợ
                .into(holder.img_layout_item_dagiao_avata);
        holder.txt_layout_item_dagiao_aotreem.setText(item.getName());
        try {
            int price = Integer.parseInt(item.getPrice()
                    .replace(",", "")
                    .replace("K", "")
                    .trim());
            int tongTien = price * item.getQuantity();
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            holder.txt_layout_item_dagiao_giatien.setText(formatter.format(tongTien));
        } catch (Exception e) {
            holder.txt_layout_item_dagiao_giatien.setText(item.getPrice());
        }


        holder.txt_layout_item_dagiao_trangthai.setText(item.getStatus());
        // có thể đổi màu theo status (ví dụ)


        if (item.getStatus() != null && item.getStatus().contains("Đang giao")) {
            holder.txt_layout_item_dagiao_trangthai.setTextColor(holder.itemView.getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            holder.txt_layout_item_dagiao_trangthai.setTextColor(holder.itemView.getResources().getColor(android.R.color.black));
        }


        holder.txt_layout_item_dagiao_mualai.setOnClickListener(item1 -> {

        });
        holder.txt_layout_item_dagiao_danhgia.setOnClickListener(item1 -> {
            Intent intent = new Intent(context, ProductReview_Activity.class);
            intent.putExtra("productName", item.getName());
            intent.putExtra("userName", item.getFullName()); // nếu model có fullName
            intent.putExtra("address", item.getAddress());

            intent.putExtra("price", item.getPrice());
            intent.putExtra("imageresid", item.getImageresid());
            intent.putExtra("orderId", item.getId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return trangThaiModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_layout_item_dagiao_avata;
        TextView txt_layout_item_dagiao_aotreem;
        TextView txt_layout_item_dagiao_tongtien;
        TextView txt_layout_item_dagiao_giatien;
        TextView txt_layout_item_dagiao_vnd;
        TextView txt_layout_item_dagiao_trangthai;
        TextView txt_layout_item_dagiao_mualai;
        TextView txt_layout_item_dagiao_danhgia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_layout_item_dagiao_avata = itemView.findViewById(R.id.img_layout_item_dagiao_avata);
            txt_layout_item_dagiao_aotreem = itemView.findViewById(R.id.txt_layout_item_dagiao_aotreem);
            txt_layout_item_dagiao_tongtien = itemView.findViewById(R.id.txt_layout_item_dagiao_tongtien);
            txt_layout_item_dagiao_giatien = itemView.findViewById(R.id.txt_layout_item_dagiao_giatien);
            txt_layout_item_dagiao_vnd = itemView.findViewById(R.id.txt_layout_item_dagiao_vnd);
            txt_layout_item_dagiao_trangthai = itemView.findViewById(R.id.txt_layout_item_dagiao_trangthai);
            txt_layout_item_dagiao_mualai = itemView.findViewById(R.id.txt_layout_item_dagiao_mualai);
            txt_layout_item_dagiao_danhgia = itemView.findViewById(R.id.txt_layout_item_dagiao_danhgia);
        }
    }
}
