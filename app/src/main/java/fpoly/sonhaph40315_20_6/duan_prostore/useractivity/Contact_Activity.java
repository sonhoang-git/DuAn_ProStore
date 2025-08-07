package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.LienHe_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.NguoiDung_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation.UserFragment;
import fpoly.sonhaph40315_20_6.duan_prostore.model.LienHe_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.NguoiDung_Model;

public class Contact_Activity extends AppCompatActivity {

   private ImageButton btn_back;
   private EditText edtname,edtphone,edtemail,edtaddress,edtnoidung;
   private Button btnSend;

   private LienHe_Dao lienHe_dao;
   private NguoiDung_Dao nguoiDung_dao;
   private LienHe_Model lienHeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        btn_back = findViewById(R.id.btn_back);
        edtname = findViewById(R.id.edtName);
        edtphone = findViewById(R.id.edtPhone);
        edtemail = findViewById(R.id.edtEmail);
        edtaddress = findViewById(R.id.edtAddress);
        edtnoidung = findViewById(R.id.edtnoidung);
        btnSend = findViewById(R.id.btnSend);
        nguoiDung_dao = new NguoiDung_Dao(this);
        lienHe_dao = new LienHe_Dao(this);
        load_NguoiDung();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    save_LienHe();
                    Toast.makeText(Contact_Activity.this, "Lưu thành công dữ liệu đã được gửi đến admin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean validate() {

        boolean isVaid = true;
        String fullName = edtname.getText().toString().trim();
        String email = edtemail.getText().toString().trim();
        String phone = edtphone.getText().toString().trim();
        String address = edtaddress.getText().toString().trim();
        String noidung = edtnoidung.getText().toString().trim();

        if (fullName.length() < 5) {
            edtname.setError("Tên phải trên 5 ký tự");
            isVaid = false;
        }

        if (!email.matches("^[\\w.-]+@gmail\\.com$")) {
            edtemail.setError("Email phải đúng định dạng ...@gmail.com");
            isVaid = false;
        }

        if (!phone.matches("^0\\d{9}$")) {
            edtphone.setError("Số điện thoại phải đủ 10 số và bắt đầu bằng 0");
            isVaid = false;
        }

        if (address.isEmpty()) {
            edtaddress.setError("Không được để trống");
            isVaid =false;
        }
        if (noidung.length() < 20) {
            edtnoidung.setError("Tên phải trên 5 ký tự");
            isVaid = false;
        }
        return isVaid;
    }

    private void save_LienHe(){
        LienHe_Model lienHeModel1 = new LienHe_Model(
                lienHeModel != null ? lienHeModel.getId() : 0,
                edtname.getText().toString(),
                edtaddress.getText().toString(),
                edtemail.getText().toString(),
                edtnoidung.getText().toString(),
                edtphone.getText().toString()
        );

            lienHe_dao.add_LienHe(lienHeModel1);
    }

    private void load_NguoiDung(){
        NguoiDung_Model nguoiDung_model = nguoiDung_dao.get_NguoiDung();
        if (nguoiDung_model != null) {
            edtname.setText(nguoiDung_model.getFullname());
            edtemail.setText(nguoiDung_model.getEmail());
            edtphone.setText(nguoiDung_model.getPhone());
            edtaddress.setText(nguoiDung_model.getAddress());
        }
    }
}