package fpoly.sonhaph40315_20_6.duan_prostore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Model.DanhGia;
import fpoly.sonhaph40315_20_6.duan_prostore.R;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.ViewHolder> {
    private Context context;
    private List<DanhGia> fullList; // Danh sách gốc
    private List<DanhGia> filteredList; // Danh sách hiển thị

    public DanhGiaAdapter(Context context, List<DanhGia> list) {
        this.context = context;
        this.fullList = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
    }

    public void filterByStars(int sao) {
        if (sao == 0) {
            filteredList = new ArrayList<>(fullList);
        } else {
            filteredList = new ArrayList<>();
            for (DanhGia dg : fullList) {
                if (dg.getSoSao() == sao) {
                    filteredList.add(dg);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setData(List<DanhGia> list) {
        this.fullList = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhGiaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danhgia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaAdapter.ViewHolder holder, int position) {
        DanhGia dg = filteredList.get(position);
        holder.tvTenKhach.setText(dg.getTenKhachHang());
        holder.tvNoiDung.setText(dg.getNoiDung().isEmpty() ? "(Không có nội dung)" : dg.getNoiDung());
        holder.tvThoiGian.setText("🕒 " + dg.getThoiGian());

        // Hiển thị sao
        int sao = dg.getSoSao();
        ImageView[] stars = { holder.star1, holder.star2, holder.star3, holder.star4, holder.star5 };
        for (int i = 0; i < 5; i++) {
            stars[i].setImageResource(i < sao ? R.drawable.ic_star_filled : R.drawable.ic_star_outline);
        }
    }

    @Override
    public int getItemCount() {
        return filteredList != null ? filteredList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhach, tvNoiDung, tvThoiGian;
        ImageView star1, star2, star3, star4, star5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhach = itemView.findViewById(R.id.tvTenKhach);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);
        }
    }
}
