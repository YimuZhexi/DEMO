package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    
    public static void startActivity(Context context){
        Intent intent = new Intent(context,CreateActivity.class);
        context.startActivity(intent);
    }

    protected ImageView cIvBackground;
    protected EditText cEtUsername, cEtPass1, cEtPass2;
    protected Button cBtnNext, cBtnCancel;
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initView();
        cBtnNext.setOnClickListener(this);
        cBtnCancel.setOnClickListener(this);
    }

    private void initView() {
        cIvBackground = findViewById(R.id.iv_create_background);
        cEtUsername = findViewById(R.id.et_create_username);
        cEtPass1 = findViewById(R.id.et_create_password1);
        cEtPass2 = findViewById(R.id.et_create_password2);
        cBtnNext = findViewById(R.id.btn_create_next);
        cBtnCancel = findViewById(R.id.btn_create_cancel);
        sharedPreferences = getSharedPreferences("date", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_next:
                String username = cEtUsername.getText().toString();
                String password1 = cEtPass1.getText().toString();
                String password2 = cEtPass2.getText().toString();
                create(username, password1, password2);
                break;
            case R.id.btn_create_cancel:
                Toast.makeText(this, "返回上一级", Toast.LENGTH_SHORT).show();
                returnMain();
                break;
        }
    }

    private void create(String username, String password1, String password2) {
        if (password1.equals(password2)) {
            editor.remove("username");
            editor.remove("password");
            editor.putString("username", username);
            editor.putString("password", password1);
            editor.commit();
            Toast.makeText(this, "用户名和密码修改成功", Toast.LENGTH_SHORT).show();
            returnMain();
        }else{
            Toast.makeText(this, "两次密码不对应，请检查", Toast.LENGTH_SHORT).show();
        }
    }

    private void returnMain(){
        MainActivity.startActivity(this);
    }
}