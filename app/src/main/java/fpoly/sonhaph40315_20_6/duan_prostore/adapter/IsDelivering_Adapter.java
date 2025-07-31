package fpoly.sonhaph40315_20_6.duan_prostore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.StatusOrder_Model;

public class IsDelivering_Adapter extends RecyclerView.Adapter<IsDelivering_Adapter.ViewHolder>{
    private final Context context;

    private final ArrayList<StatusOrder_Model> trangThaiModelArrayList;


    public IsDelivering_Adapter(Context context, ArrayList<StatusOrder_Model> trangThaiModelArrayList) {
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
        StatusOrder_Model item = trangThaiModelArrayList.get(position);
        holder.img_layout_item_danggiao_avata.setImageResource(item.getAvata());
        holder.txt_layout_item_danggiao_aotreem.setText(item.getName());
        holder.txt_layout_item_danggiao_trangthai.setText(item.getTrangthai());
        holder.txt_layout_item_danggiao_giatien.setText(String.valueOf(item.getGia()+ "K"));
        holder.txt_layout_item_danggiao_xemthongtin.setOnClickListener(item1 ->{

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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_layout_item_danggiao_avata = itemView.findViewById(R.id.img_layout_item_danggiao_avata);
            txt_layout_item_danggiao_aotreem = itemView.findViewById(R.id.txt_layout_item_danggiao_aotreem);
            txt_layout_item_danggiao_tongtien = itemView.findViewById(R.id.txt_layout_item_danggiao_tongtien);
            txt_layout_item_danggiao_giatien = itemView.findViewById(R.id.txt_layout_item_danggiao_giatien);
            txt_layout_item_danggiao_vnd = itemView.findViewById(R.id.txt_layout_item_danggiao_vnd);
            txt_layout_item_danggiao_trangthai = itemView.findViewById(R.id.txt_layout_item_danggiao_trangthai);
            txt_layout_item_danggiao_xemthongtin = itemView.findViewById(R.id.txt_layout_item_danggiao_xemthongtin);
        }
    }
}
