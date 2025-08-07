package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DanhGia_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DanhGia_Model;

public class ProductReview_Activity extends AppCompatActivity {

    ImageButton btn_back;
    ImageView[] stars = new ImageView[5];
    EditText edtNoiDung;
    TextView tvTenSanPham, tvGia, tvTenUser, tvDiaChi;
    ImageView imgSanPham;
    Button btnSubmit;
    int rating = 0;
    DanhGia_Dao danhGiaDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_review);
        btn_back = findViewById(R.id.btn_back);
        edtNoiDung = findViewById(R.id.edt_danhgia_noidung);
        tvTenSanPham = findViewById(R.id.txt_danhgia_namesanpham);
        tvGia = findViewById(R.id.txt_danhgia_price);
        tvTenUser = findViewById(R.id.txt_danhgia_nameuser);
        tvDiaChi = findViewById(R.id.txt_danhgia_diachi);
        imgSanPham = findViewById(R.id.img_layout_item_choxacnhan_avata);
        btnSubmit = findViewById(R.id.btnSubmit);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        ImageView[] stars = {
//                findViewById(R.id.star1),
//                findViewById(R.id.star2),
//                findViewById(R.id.star3),
//                findViewById(R.id.star4),
//                findViewById(R.id.star5)
//        };

        stars[0] = findViewById(R.id.star1);
        stars[1] = findViewById(R.id.star2);
        stars[2] = findViewById(R.id.star3);
        stars[3] = findViewById(R.id.star4);
        stars[4] = findViewById(R.id.star5);

        int yellow = ContextCompat.getColor(this, R.color.colorPrimary);
        int gray = ContextCompat.getColor(this, R.color.gray);

        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i].setOnClickListener(v -> {
                for (int j = 0; j < stars.length; j++) {
                    if (j <= index) {
                        stars[j].setColorFilter(yellow, PorterDuff.Mode.SRC_IN);
                    } else {
                        stars[j].setColorFilter(gray, PorterDuff.Mode.SRC_IN);
                    }
                }
            });

        }

    }
}

