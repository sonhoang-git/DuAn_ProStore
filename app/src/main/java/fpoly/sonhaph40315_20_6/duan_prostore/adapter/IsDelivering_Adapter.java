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

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.useractivity.OrderInformation_Activity;
import fpoly.sonhaph40315_20_6.duan_prostore.useractivity.ProductReview_Activity;

public class IsDelivering_Adapter extends RecyclerView.Adapter<IsDelivering_Adapter.ViewHolder>{
    private final Context context;

    private final ArrayList<DonHang_Model> trangThaiModelArrayList;


    public IsDelivering_Adapter(Context context, ArrayList<DonHang_Model> trangThaiModelArrayList) {
        this.context = context;
        this.trangThaiModelArrayList = trangThaiModelArrayList;
    }
    @NonNull
    @Override
    public IsDelivering_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_isdelivering_fragment, parent, false);
        return new IsDelivering_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IsDelivering_Adapter.ViewHolder holder, int position) {
        DonHang_Model item = trangThaiModelArrayList.get(position);
        Glide.with(context)
                .load(item.getImageresid())  // nếu là resource ID: int, Glide vẫn hỗ trợ
                .into(holder.img_layout_item_danggiao_avata);
        holder.txt_layout_item_danggiao_aotreem.setText(item.getName());
        holder.txt_layout_item_danggiao_trangthai.setText(item.getStatus());
        holder.txt_layout_item_danggiao_giatien.setText(item.getPrice()+ "K");
        holder.txt_layout_item_danggiao_soluong.setText(String.valueOf(item.getQuantity()));
        holder.txt_layout_item_danggiao_xemthongtin.setOnClickListener(item1 ->{
            Intent intent = new Intent(context, OrderInformation_Activity.class);
            intent.putExtra("tensp", item.getName());
            intent.putExtra("gia", item.getPrice());
            intent.putExtra("avata", item.getImageresid());
            intent.putExtra("soluong", item.getQuantity());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return trangThaiModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_layout_item_danggiao_avata;
        TextView txt_layout_item_danggiao_aotreem;

        TextView txt_layout_item_danggiao_tongtien;
        TextView txt_layout_item_danggiao_giatien;
        TextView txt_layout_item_danggiao_vnd;
        TextView txt_layout_item_danggiao_trangthai;
        TextView txt_layout_item_danggiao_xemthongtin;
        TextView txt_layout_item_danggiao_soluong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_layout_item_danggiao_avata = itemView.findViewById(R.id.img_layout_item_danggiao_avata);
            txt_layout_item_danggiao_aotreem = itemView.findViewById(R.id.txt_layout_item_danggiao_aotreem);
            txt_layout_item_danggiao_tongtien = itemView.findViewById(R.id.txt_layout_item_danggiao_tongtien);
            txt_layout_item_danggiao_giatien = itemView.findViewById(R.id.txt_layout_item_danggiao_giatien);
            txt_layout_item_danggiao_vnd = itemView.findViewById(R.id.txt_layout_item_danggiao_vnd);
            txt_layout_item_danggiao_trangthai = itemView.findViewById(R.id.txt_layout_item_danggiao_trangthai);
            txt_layout_item_danggiao_xemthongtin = itemView.findViewById(R.id.txt_layout_item_danggiao_xemthongtin);
            txt_layout_item_danggiao_soluong = itemView.findViewById(R.id.txt_layout_item_danggiao_soluong);
        }
    }
}
