package com.example.luojiaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button1 = (Button) findViewById(R.id.submit);
        final EditText password1 = (EditText) findViewById(R.id.password1);
        final EditText password2 = (EditText) findViewById(R.id.password2);
        final EditText name = (EditText) findViewById(R.id.name);

        password1.setInputType(129);
        password2.setInputType(129);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = name.getText().toString();
                final String pass1 = password1.getText().toString();
                final String pass2 = password2.getText().toString();

                if ("?".equals(username)) {
                    Toast.makeText(RegisterActivity.this, "用户名包含非法字符!", Toast.LENGTH_LONG).show();
                } else if (!pass1.equals(pass2)) {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致!", Toast.LENGTH_LONG).show();
                } else {
                    String userid = ServerConnection.registerCommonUser(username, pass1);
                    if (userid != null && userid.length() == 5) {  // 规定服务器返回的用户 id 一定是五位
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        // 注册成功则自动登陆
                        LoginStatus.loggedin = true;
                        LoginStatus.username = username;
                        LoginStatus.userid = userid;
                        Intent intent = new Intent(RegisterActivity.this, RegisterSucActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "网络或服务器异常", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
