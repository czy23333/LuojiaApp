package com.example.luojiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luojiaapp.LoginStatus;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        TextView name=(TextView)findViewById(R.id.user_name_string);
        TextView id=(TextView)findViewById(R.id.user_id_string);
        id.setText(LoginStatus.userid);
        name.setText(LoginStatus.username);
        Button back=(Button)findViewById(R.id.back2);
        Button seller=(Button)findViewById(R.id.seller);
        Button cart = (Button) findViewById(R.id.shopping_cart);
        Button purchased = (Button) findViewById(R.id.purchased);
        back.setOnClickListener(new View.OnClickListener() {//点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InformationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {//点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InformationActivity.this, SellerActivity.class);
                startActivity(intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        purchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, ItemBoughtActivity.class);
                startActivity(intent);
            }
        });
    }
}
