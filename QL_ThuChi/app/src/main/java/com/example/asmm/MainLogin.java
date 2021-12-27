package com.example.asmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainLogin extends AppCompatActivity {
    EditText tvUser,tvPass;
    CheckBox checkbox;
    Button btnLogin,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        tvUser = findViewById(R.id.tvUserName);
        tvPass = findViewById(R.id.tvPassWord);
        checkbox = findViewById(R.id.checkbox);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tvUser.getText().toString();
                String pass = tvPass.getText().toString();
                if(user.equals("ps11587") && pass.equals("123456"))
                {
                    Intent intent = new Intent(MainLogin.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (user.isEmpty() || pass.isEmpty()) {
                    tvUser.setError("Khong duoc bo trong");
                    tvPass.setError("Khong duoc bo trong");
                }
                else {
                    tvUser.setError("Sai tai khoan hoac mat khau");
                    tvPass.setError("Sai tai khoan hoac mat khau");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPass.setText("");
                tvUser.setText("");
            }
        });
        if(checkbox.isSelected()==true)
        {
            tvUser.setText("ps11587");
            tvPass.setText("123456");
        }
    }
}
