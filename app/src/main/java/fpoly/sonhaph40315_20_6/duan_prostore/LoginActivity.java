package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import fpoly.sonhaph40315_20_6.duan_prostore.Dao.UserDao;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnSignIn, btnGoogle;
    private TextView txtRegister, txtReset;
    private CheckBox chkRemember;
    private SharedPreferences sharedPreferences;

    private UserDao userDAO;
    public static final String PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Ánh xạ view
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnGoogle = findViewById(R.id.btnGoogle);
        txtRegister = findViewById(R.id.txtRegister);
        txtReset = findViewById(R.id.txtReset);
        chkRemember = findViewById(R.id.chkRemember);

        userDAO = new UserDao(this);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load Remember
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");
        boolean savedRemember = sharedPreferences.getBoolean("remember", false);

        if (savedRemember) {
            edtEmail.setText(savedEmail);
            edtPassword.setText(savedPassword);
            chkRemember.setChecked(true);
        }

        // Chuyển sang Register
        txtRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // Đăng nhập
        btnSignIn.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = userDAO.checkLogin(email, password);
            if (user != null) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                if (chkRemember.isChecked()) {
                    sharedPreferences.edit()
                            .putString("email", email)
                            .putString("password", password)
                            .putBoolean("remember", true)
                            .apply();
                } else {
                    sharedPreferences.edit().clear().apply();
                }

                // 👉 Chuyển trang theo vai trò
                if ("admin".equals(user.getRole())) {
                    startActivity(new Intent(this, AdminActivity.class));
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                }
                finish();
            } else {
                Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });

        // Hiện/ẩn mật khẩu
        final boolean[] isPasswordVisible = {false};
        edtPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (edtPassword.getRight()
                        - edtPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                    if (isPasswordVisible[0]) {
                        edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        edtPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);
                    } else {
                        edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        edtPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);
                    }
                    isPasswordVisible[0] = !isPasswordVisible[0];
                    edtPassword.setSelection(edtPassword.getText().length());
                    return true;
                }
            }
            return false;
        });
    }
}
