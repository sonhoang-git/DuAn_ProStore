package fpoly.sonhaph40315_20_6.duan_prostore;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fpoly.sonhaph40315_20_6.duan_prostore.Model.SanPham;

public class SanPhamChiTietFragment extends Fragment {

    private TextView tvName, tvPrice, tvSize, tvCategory, tvQuantity, tvDate;
    private ImageView imgProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_san_pham_chi_tiet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvName = view.findViewById(R.id.tvName);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvSize = view.findViewById(R.id.tvSize);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvQuantity = view.findViewById(R.id.tvQuantity);
        tvDate = view.findViewById(R.id.tvDate);
        imgProduct = view.findViewById(R.id.imgProduct);

        Bundle bundle = getArguments();
        if (bundle != null) {
            SanPham sp = (SanPham) bundle.getSerializable("sanpham");
            if (sp != null) {
                tvName.setText(sp.getName());
                tvPrice.setText("Giá: " + String.format("%,.0f", sp.getPrice()) + " VNĐ");
                tvSize.setText("Size: " + sp.getSize());
                tvCategory.setText("Loại: " + sp.getCategory());
                tvQuantity.setText("Số lượng: " + sp.getQuantity());
                tvDate.setText("Ngày: " + sp.getDate());

                // Dùng Glide để load ảnh
                if (sp.getImagePath() != null && !sp.getImagePath().isEmpty()) {
                    Glide.with(requireContext())
                            .load(Uri.parse(sp.getImagePath()))
                            .placeholder(R.drawable.ic_product)
                            .into(imgProduct);
                } else {
                    imgProduct.setImageResource(R.drawable.ic_product);
                }
            }
        }
    }
}
