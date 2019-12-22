package com.example.luojiaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button back = findViewById(R.id.back);
        Button register = findViewById(R.id.register);
        Button login = findViewById(R.id.login);
        final EditText useridEditText = findViewById(R.id.account);
        final EditText passwordEditText = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {   //点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                String userid = useridEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String username = ServerConnection.verifyPassword(userid, password);
                if (username != null) {
                    LoginStatus.loggedin = true;
                    LoginStatus.userid = userid;
                    LoginStatus.username = username;
                    Toast.makeText(LoginActivity.this, "登录成功: [" + username + "]", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, PersonalCenterActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "密码错误或网络异常", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {//点击返回按钮触发效果
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {//点击注册按钮触发效果
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
