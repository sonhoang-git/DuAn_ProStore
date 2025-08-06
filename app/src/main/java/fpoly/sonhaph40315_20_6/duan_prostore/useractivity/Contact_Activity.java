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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation.UserFragment;

public class Contact_Activity extends AppCompatActivity {

   private ImageButton btn_back;
   private EditText edtname,edtphone,edtemail,edtaddress,edtmessage;
   private Button btngui;

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
        edtmessage = findViewById(R.id.edtMessage);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}