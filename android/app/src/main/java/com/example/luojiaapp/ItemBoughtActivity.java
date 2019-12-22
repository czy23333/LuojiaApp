package com.example.luojiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemBoughtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bought);
        Button back = (Button) findViewById(R.id.back2);
        Button seller = (Button) findViewById(R.id.seller);
        Button information = (Button) findViewById(R.id.information);
        Button cart = (Button) findViewById(R.id.shopping_cart);

        // 设置监听按钮的点击, 切换页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemBoughtActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemBoughtActivity.this, SellerActivity.class);
                startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemBoughtActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemBoughtActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }
}
