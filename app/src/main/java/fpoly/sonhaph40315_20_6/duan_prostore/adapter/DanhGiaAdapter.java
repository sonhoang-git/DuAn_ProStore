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
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DanhGia_Model;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.ViewHolder> {
    private final Context context;
    private List<DanhGia_Model> fullList;
    private List<DanhGia_Model> filteredList;

    public DanhGiaAdapter(Context context, List<DanhGia_Model> list) {
        this.context = context;
        this.fullList = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
    }

    public void filterByStars(int sao) {
        if (sao == 0) {
            filteredList = new ArrayList<>(fullList);
        } else {
            List<DanhGia_Model> temp = new ArrayList<>();
            for (DanhGia_Model dg : fullList) {
                if (dg.getRating() == sao) {
                    temp.add(dg);
                }
            }
            filteredList = temp;
        }
        notifyDataSetChanged();
    }

    public void setData(List<DanhGia_Model> list) {
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
        DanhGia_Model danhGiaModel = filteredList.get(position);
        holder.bind(danhGiaModel);
    }

    @Override
    public int getItemCount() {
        return filteredList != null ? filteredList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTenKhach, tvNoiDung, tvThoiGian;
        private final ImageView[] stars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhach = itemView.findViewById(R.id.tvTenKhach);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            stars = new ImageView[]{
                    itemView.findViewById(R.id.star1),
                    itemView.findViewById(R.id.star2),
                    itemView.findViewById(R.id.star3),
                    itemView.findViewById(R.id.star4),
                    itemView.findViewById(R.id.star5)
            };
        }

        public void bind(DanhGia_Model dg) {
            tvTenKhach.setText(dg.getNamesanpham());
            tvNoiDung.setText(dg.getNoidung().isEmpty() ? "(Không có nội dung)" : dg.getNoidung());
            tvThoiGian.setText("🕒 " + dg.getPrice());

            int soSao = dg.getRating();
            for (int i = 0; i < 5; i++) {
                stars[i].setImageResource(i < soSao ? R.drawable.ic_star_filled : R.drawable.ic_star_outline);
            }
        }
    }
}
