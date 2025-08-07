package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.NguoiDung_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.model.NguoiDung_Model;

public class OrderInformation_Activity extends AppCompatActivity {
    ImageButton btn_back;
    private TextView txt_oderInfor_nameusser, txt_oderInfor_address;
    private TextView txt_oderInfor_namesanpham, txt_oderInfor_price, txt_oderInfor_soluong;
    private TextView txt_oderInfor_huydonhang, txt_oderInfor_lienhe;
    private ImageView img_oderInfor_avata;

    private NguoiDung_Model nguoiDung_model;
    private NguoiDung_Dao nguoiDung_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_information);
        btn_back = findViewById(R.id.btn_back);
        txt_oderInfor_nameusser = findViewById(R.id.txt_oderInfor_nameusser);
        txt_oderInfor_address = findViewById(R.id.txt_oderInfor_address);
        txt_oderInfor_namesanpham = findViewById(R.id.txt_oderInfor_namesanpham);
        txt_oderInfor_price = findViewById(R.id.txt_oderInfor_price);
        txt_oderInfor_soluong = findViewById(R.id.txt_oderInfor_soluong);
        txt_oderInfor_huydonhang = findViewById(R.id.txt_oderInfor_huydonhang);
        txt_oderInfor_lienhe = findViewById(R.id.txt_oderInfor_lienhe);
        img_oderInfor_avata = findViewById(R.id.img_oderInfor_avata);
        nguoiDung_dao = new NguoiDung_Dao(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String tensp = getIntent().getStringExtra("tensp");
        String gia = getIntent().getStringExtra("gia");
        int avata = getIntent().getIntExtra("avata", 0);
        int soluong = getIntent().getIntExtra("soluong",0);

        txt_oderInfor_namesanpham.setText(tensp);
        txt_oderInfor_price.setText(gia+"K");
        txt_oderInfor_soluong.setText(String.valueOf(soluong));
        img_oderInfor_avata.setImageResource(avata);

        nguoiDung_model = nguoiDung_dao.get_NguoiDung();
        if (nguoiDung_model != null) {
            txt_oderInfor_nameusser.setText(nguoiDung_model.getFullname());
            txt_oderInfor_address.setText(nguoiDung_model.getAddress());
        }
    }
}