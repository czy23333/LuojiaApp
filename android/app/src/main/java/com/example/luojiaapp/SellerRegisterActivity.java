package com.example.luojiaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SellerRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        Toast.makeText(SellerRegisterActivity.this, "请先注册成为商人", Toast.LENGTH_SHORT).show();

        Button submit = (Button) findViewById(R.id.submit2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realname = ((EditText) findViewById(R.id.seller_name)).getText().toString();
                String contact = ((EditText) findViewById(R.id.connect)).getText().toString();
                String department = ((EditText) findViewById(R.id.add)).getText().toString();
                String school = ((EditText) findViewById(R.id.department)).getText().toString();
                String grade = ((EditText) findViewById(R.id.grade)).getText().toString();

                if (realname.length() == 0 || contact.length() == 0 || department.length() == 0
                        || school.length() == 0 || grade.length() == 0) {
                    Toast.makeText(SellerRegisterActivity.this, "请填写每一项信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                String infos = realname + ";" + contact + ";" + department + ";" + school + ";" + grade;
                if (ServerConnection.registerSeller(infos)) {
                    // 注册成功则自动跳转到商人信息页面
                    LoginStatus.realname = realname;
                    LoginStatus.contact = contact;
                    LoginStatus.department = department;
                    LoginStatus.school = school;
                    LoginStatus.grade = grade;
                    Toast.makeText(SellerRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SellerRegisterActivity.this, SellerActivity.class));
                } else {
                    Toast.makeText(SellerRegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
