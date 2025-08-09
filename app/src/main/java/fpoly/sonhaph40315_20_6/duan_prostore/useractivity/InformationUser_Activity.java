package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.dao.LienHe_Dao;
import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.LienHe_Model;

public class InformationUser_Activity extends AppCompatActivity {

    ImageButton btn_back;
    EditText edtName, edtEmail, edtPhone, edtAddress;
    Button btnSave;

    LienHe_Dao lienHeDao;
    LienHe_Model currentContact = null; // Liên hệ hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);

        btn_back = findViewById(R.id.btn_back);
        edtName = findViewById(R.id.edt_information_name);
        edtEmail = findViewById(R.id.edt_information_email);
        edtPhone = findViewById(R.id.edt_information_phone);
        edtAddress = findViewById(R.id.edt_information_address);
        btnSave = findViewById(R.id.btn_information_save);

        lienHeDao = new LienHe_Dao(this);

        btn_back.setOnClickListener(v -> finish());

        // Load liên hệ mới nhất
        List<LienHe_Model> contactList = lienHeDao.getAllLienHe();
        if (!contactList.isEmpty()) {
            currentContact = contactList.get(contactList.size() - 1);
            edtName.setText(currentContact.getFullname());
            edtEmail.setText(currentContact.getEmail());
            edtPhone.setText(currentContact.getPhone());
            edtAddress.setText(currentContact.getAddress());
        }

        // Cập nhật thông tin
        btnSave.setOnClickListener(v -> {
            if (currentContact == null) {
                Toast.makeText(this, "Không có thông tin để cập nhật", Toast.LENGTH_SHORT).show();
                return;
            }

            String newName = edtName.getText().toString().trim();
            String newEmail = edtEmail.getText().toString().trim();
            String newPhone = edtPhone.getText().toString().trim();
            String newAddress = edtAddress.getText().toString().trim();

            currentContact.setFullname(newName);
            currentContact.setEmail(newEmail);
            currentContact.setPhone(newPhone);
            currentContact.setAddress(newAddress);

            boolean updated = lienHeDao.updateContact(currentContact);
            if (updated) {
                Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
