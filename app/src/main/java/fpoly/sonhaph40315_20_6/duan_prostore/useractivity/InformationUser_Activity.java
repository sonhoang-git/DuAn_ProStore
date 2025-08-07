package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.dao.NguoiDung_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation.UserFragment;
import fpoly.sonhaph40315_20_6.duan_prostore.model.NguoiDung_Model;

public class InformationUser_Activity extends AppCompatActivity {

    ImageButton btn_back;
    private EditText edt_information_name, edt_information_email, edt_information_phone, edt_information_address;
    private Button btn_information_save;

    private NguoiDung_Dao nguoiDungDao;
    private NguoiDung_Model nguoiDung_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information_user);
        btn_back = findViewById(R.id.btn_back);
        edt_information_name = findViewById(R.id.edt_information_name);
        edt_information_email = findViewById(R.id.edt_information_email);
        edt_information_phone = findViewById(R.id.edt_information_phone);
        edt_information_address = findViewById(R.id.edt_information_address);
        btn_information_save = findViewById(R.id.btn_information_save);

        nguoiDungDao = new NguoiDung_Dao(this);
        load_NguoiDung();
        btn_information_save.setOnClickListener(item -> {
            if(validate()) save_NguoiDung();
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
        String fullName = edt_information_name.getText().toString().trim();
        String email = edt_information_email.getText().toString().trim();
        String phone = edt_information_phone.getText().toString().trim();
        String address = edt_information_address.getText().toString().trim();

        if (fullName.length() < 5) {
            edt_information_name.setError("Tên phải trên 5 ký tự");
            isVaid = false;
        }

        if (!email.matches("^[\\w.-]+@gmail\\.com$")) {
            edt_information_email.setError("Email phải đúng định dạng ...@gmail.com");
            isVaid = false;
        }

        if (!phone.matches("^0\\d{9}$")) {
            edt_information_phone.setError("Số điện thoại phải đủ 10 số và bắt đầu bằng 0");
            isVaid = false;
        }

        if (address.isEmpty()) {
            edt_information_address.setError("Không được để trống");
            isVaid =false;
        }

        return isVaid;
    }

    private void save_NguoiDung(){
       NguoiDung_Model nguoiDung_model1 = new NguoiDung_Model(
                nguoiDung_model != null ? nguoiDung_model.getId() : 0,
                edt_information_name.getText().toString(),
                edt_information_email.getText().toString(),
                edt_information_phone.getText().toString(),
                edt_information_address.getText().toString()
        );
       if(nguoiDung_model != null){
           nguoiDungDao.updateUser(nguoiDung_model1);
       }else{
           nguoiDungDao.add_NguoiDung(nguoiDung_model1);
       }
    }
    private void load_NguoiDung(){
        nguoiDung_model = nguoiDungDao.get_NguoiDung();
        if (nguoiDung_model != null) {
            edt_information_name.setText(nguoiDung_model.getFullname());
            edt_information_email.setText(nguoiDung_model.getEmail());
            edt_information_phone.setText(nguoiDung_model.getPhone());
            edt_information_address.setText(nguoiDung_model.getAddress());
        }
    }
}