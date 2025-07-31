package fpoly.sonhaph40315_20_6.duan_prostore.useractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.fragmentbottomnavigation.WaitingForConfirmation_Fragment;

public class Order_Activity extends AppCompatActivity {

    ImageView img_choxacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        img_choxacnhan = findViewById(R.id.img_choxacnhan);
        img_choxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_Activity.this, OderStatus_Activity.class);
                startActivity(intent);
            }
        });

    }
}