package com.example.asmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmm.dao.QuanTriDao;
import com.example.asmm.model.User;

public class Loginl extends AppCompatActivity {
    EditText txtTen,txtMk;
    Button btnDn;
    CheckBox checked;
    public static User USER = null;
    QuanTriDao qtDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginl);
        qtDao = new QuanTriDao(Loginl.this);
        txtTen= findViewById(R.id.txtUser);
        txtMk = findViewById(R.id.txtPass);
        checked= findViewById(R.id.txtC);
        btnDn = findViewById(R.id.btnLogin);
        loadData();

        btnDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uss = txtTen.getText().toString().trim();
                String pss = txtMk.getText().toString().trim();
                boolean check = checked.isChecked();
                User user = new User(uss,pss);
                if(qtDao.checkLogin(user)){
                    luuTT(uss,pss,check);
                    USER = user;
                    Toast.makeText(Loginl.this,"Đăng nhập thanh công",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Loginl.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Loginl.this,"Thất bại!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void luuTT(String un,String pw,boolean check){
        SharedPreferences preferences = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(check){
            editor.putString("username",un);
            editor.putString("password",pw);
            editor.putBoolean("check",check);
        }else{
            editor.clear();
        }
        editor.commit();
    }

    private void loadData(){
        SharedPreferences pref = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        boolean check = pref.getBoolean("check",false);
        if(check){
            txtTen.setText(pref.getString("username","tony"));
            txtMk.setText(pref.getString("password","123"));
            checked.setChecked(check);
        }
    }

}