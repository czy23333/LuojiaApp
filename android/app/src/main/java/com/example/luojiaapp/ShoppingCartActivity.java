package com.example.luojiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    public static List<Item> cartList = new ArrayList<Item>();
    private static int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Button back = (Button) findViewById(R.id.back2);
        Button seller = (Button) findViewById(R.id.seller);
        Button information = (Button) findViewById(R.id.information);
        Button purchased = (Button) findViewById(R.id.purchased);

        // 设置监听按钮的点击, 切换页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, SellerActivity.class);
                startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });
        purchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, ItemBoughtActivity.class);
                startActivity(intent);
            }
        });

        // TODO initItems() 应该与数据库交互, 获取用户添加到购物车中的数据
        if(i==0) {
            //initItems();  // 初始化水果数据
            i++;
        }

        ItemAdapter adapter = new ItemAdapter(ShoppingCartActivity.this, R.layout.item, cartList);
        final ListView listView = (ListView) findViewById(R.id.cart_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ItemInformation.item = cartList.get(position);  // 设置要显示的 item
                startActivity(new Intent(ShoppingCartActivity.this, ItemInformation.class));
            }
        });
    }

    private void initItems() {
        for (int i = 0; i < 2; i++) {
            Item apple = new Item("Apple", R.drawable.apple_pic);
            cartList.add(apple);
        }
    }
}
