package dattvph16984.fpoly.dattvph16984_myasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edName, edPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        edName = findViewById(R.id.edtUserName);
        edPass = findViewById(R.id.edtPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
                if (edName.getText().toString().equals("Admin") && edPass.getText().toString().equals("Admin")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else if (edName.getText().toString().isEmpty() && edPass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Tên đăng nhập và mật khẩu không được để trống!!!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(LoginActivity.this, "Tên đăng nhập và mật khẩu k đúng !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}