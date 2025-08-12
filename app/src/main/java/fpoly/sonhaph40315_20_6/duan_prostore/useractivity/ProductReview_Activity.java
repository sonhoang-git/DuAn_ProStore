package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.DanhGia_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DanhGia_Model;

public class ProductReview_Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText edtReview;
    private Button btnSubmit;
    private ImageView[] stars;
    private ImageView img_danhgia_avata;

    private TextView txt_danhgia_namesanpham,txt_danhgia_price,txt_danhgia_nameuser,txt_danhgia_diachi;

    private int selectedRating = 0;

    private int colorYellow;
    private int colorGray;

    private String productName, userName, address,price;
    private int imageresid, orderId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);

        // Ánh xạ view
        btnBack = findViewById(R.id.btn_back);
        edtReview = findViewById(R.id.edt_danhgia_noidung);
        btnSubmit = findViewById(R.id.btnSubmit);
        txt_danhgia_namesanpham = findViewById(R.id.txt_danhgia_namesanpham);
        txt_danhgia_diachi = findViewById(R.id.txt_danhgia_diachi);
        txt_danhgia_price = findViewById(R.id.txt_danhgia_price);
        txt_danhgia_nameuser = findViewById(R.id.txt_danhgia_nameuser);
        img_danhgia_avata = findViewById(R.id.img_danhgia_avata);

        stars = new ImageView[]{
                findViewById(R.id.star1),
                findViewById(R.id.star2),
                findViewById(R.id.star3),
                findViewById(R.id.star4),
                findViewById(R.id.star5)
        };
        Intent intent = getIntent();
        productName = intent.getStringExtra("productName");
        userName = intent.getStringExtra("userName");
        address = intent.getStringExtra("address");
        price = intent.getStringExtra("price");
        imageresid = intent.getIntExtra("imageresid", 0);
        orderId = intent.getIntExtra("orderId", 0);

        txt_danhgia_namesanpham.setText(productName);
        txt_danhgia_nameuser.setText(userName);
        txt_danhgia_price.setText(price);
        txt_danhgia_diachi.setText(address);
        img_danhgia_avata.setImageResource(imageresid);

        // Lấy màu từ resources
        colorYellow = ContextCompat.getColor(this, R.color.colorPrimary);
        colorGray = ContextCompat.getColor(this, R.color.gray);

        btnBack.setOnClickListener(v -> finish());


        // Gán sự kiện click cho từng sao
        for (int i1 = 0; i1 < stars.length; i1++) {
            final int index = i1;
            stars[i1].setOnClickListener(v -> setStarRating(index + 1));
        }

        btnSubmit.setOnClickListener(v -> submitReview());
    }

    // Hàm set màu sao dựa theo số sao đã chọn
    private void setStarRating(int rating) {
        selectedRating = rating;
        for (int i = 0; i < stars.length; i++) {
            stars[i].setColorFilter(i < rating ? colorYellow : colorGray, PorterDuff.Mode.SRC_IN);
        }
    }

    private void submitReview() {
        String content = edtReview.getText().toString().trim();

        if (selectedRating == 0) {
            Toast.makeText(this, "Vui lòng chọn số sao", Toast.LENGTH_SHORT).show();
            return;
        }

        if (content.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập nội dung đánh giá", Toast.LENGTH_SHORT).show();
            return;
        }


        int priceValue = 0;
        try {
            priceValue = Integer.parseInt(price.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }           // Nếu không có giá, đặt tạm 0 hoặc lấy giá thật
        String avata = String.valueOf(imageresid);
        DanhGia_Model danhGia = new DanhGia_Model(
                0,          // id mới nên để 0
                address,
                avata,
                productName,
                userName,
                selectedRating,
                content,
                priceValue
        );

        DanhGia_Dao dao = new DanhGia_Dao(this);

        long result = dao.insertDanhGia(danhGia);
        if (result > 0) {
            Toast.makeText(this, "Gửi đánh giá thành công", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Gửi thất bại", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressWarnings("unused")
    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
    }
}


