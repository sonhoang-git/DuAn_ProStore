package fpoly.sonhaph40315_20_6.duan_prostore;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import fpoly.sonhaph40315_20_6.duan_prostore.dao.SanPham_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.database.SanPham_Database;

public class DialogSuaSanPham extends DialogFragment {

    private static final String ARG_SAN_PHAM = "sanPham";
    private EditText edtName, edtPrice, edtQuantity, edtSize, edtCategory, edtImagePath, edtDate;
    private Button btnCapNhat;
    private Product sanPham;
    private OnProductUpdatedListener listener;

    public interface OnProductUpdatedListener {
        void onUpdated();
    }

    public void setOnProductUpdatedListener(OnProductUpdatedListener listener) {
        this.listener = listener;
    }

    public static DialogSuaSanPham newInstance(Product sanPham) {
        DialogSuaSanPham fragment = new DialogSuaSanPham();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SAN_PHAM, sanPham);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sua_san_pham, null);
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(view);

        // Lấy dữ liệu sản phẩm từ arguments
        if (getArguments() != null) {
            sanPham = (Product) getArguments().getSerializable(ARG_SAN_PHAM);
        }

        // Ánh xạ view
        edtName = view.findViewById(R.id.edtName);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtQuantity = view.findViewById(R.id.edtQuantity);
        edtSize = view.findViewById(R.id.edtSize);
        edtCategory = view.findViewById(R.id.edtCategory);
        edtImagePath = view.findViewById(R.id.edtImagePath);
        edtDate = view.findViewById(R.id.edtDate);
        btnCapNhat = view.findViewById(R.id.btnCapNhat);

        // Gán dữ liệu cũ vào các EditText
        if (sanPham != null) {
            edtName.setText(sanPham.getName());
            edtPrice.setText(String.valueOf(sanPham.getPrice()));
            edtQuantity.setText(String.valueOf(sanPham.getQuantity()));
            edtSize.setText(sanPham.getSize());
            edtCategory.setText(sanPham.getCategory());

        }

        btnCapNhat.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();
            String quantityStr = edtQuantity.getText().toString().trim();
            String size = edtSize.getText().toString().trim();
            String category = edtCategory.getText().toString().trim();
            String imagePath = edtImagePath.getText().toString().trim();
            String date = edtDate.getText().toString().trim();

            if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceStr);
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Giá hoặc số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật dữ liệu sản phẩm
            sanPham.setName(name);
            sanPham.setPrice(price);
            sanPham.setQuantity(quantity);
            sanPham.setSize(size);
            sanPham.setCategory(category);

            // Tạo đối tượng dao với đúng constructor
            SanPham_Dao dao = new SanPham_Dao(new SanPham_Database(requireContext()), requireContext());

            int result = dao.updateSanPham(sanPham);
            if (result > 0) {
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                if (listener != null) listener.onUpdated();
                dismiss();
            } else {
                Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        return dialog;
    }
}
