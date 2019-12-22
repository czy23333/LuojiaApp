package com.example.luojiaapp;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PersonalCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        Button back = (Button) findViewById(R.id.back2);
        Button seller = (Button) findViewById(R.id.seller);
        Button information = (Button) findViewById(R.id.information);
        Button cart = (Button) findViewById(R.id.shopping_cart);
        Button purchased = (Button) findViewById(R.id.purchased);

        // 设置监听按钮的点击, 切换页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击 我是商人 按钮之后, 尝试从服务器获取商人信息 (若获取失败则不跳转)
                // 如果返回 null 则跳转到注册界面
                String info = ServerConnection.getSellerInfo();
                Class<?> page = SellerRegisterActivity.class;
                if (info != null) {
                    String[] infos = info.split(";");
                    if (infos.length != 5) {
                        Toast.makeText(PersonalCenterActivity.this, "服务器返回信息异常", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    LoginStatus.realname = infos[0];
                    LoginStatus.contact = infos[1];
                    LoginStatus.department = infos[2];
                    LoginStatus.school = infos[3];
                    LoginStatus.grade = infos[4];
                    page = SellerActivity.class;
                }

                Intent intent = new Intent(PersonalCenterActivity.this, page);
                startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        purchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, ItemBoughtActivity.class);
                startActivity(intent);
            }
        });
    }
}
