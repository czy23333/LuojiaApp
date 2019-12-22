package com.example.luojiaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

public class ItemInformation extends AppCompatActivity {
    public static Item item;        // 全局变量传递信息, 表示当前要显示的 item 信息
    private static final String TAG = "ItemInformation";
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_information);

        TextView price_textview = (TextView)findViewById(R.id.item_price);
        TextView sales_textview = (TextView)findViewById(R.id.item_sales);
        TextView name_textview = (TextView)findViewById(R.id.item_description);
        ImageView item_image = (ImageView)findViewById(R.id.image_item2);

        name_textview.setText(item.getName());
        price_textview.setText(String.valueOf(item.getPrice()));
        sales_textview.setText(String.valueOf(item.getAmount()));
        item_image.setImageBitmap(item.getBitmap());

        final Button button_add_collect = (Button)findViewById(R.id.item_collection);
        for (i = 0;i<ShoppingCartActivity.cartList.size();i++ ){
            if(ShoppingCartActivity.cartList.get(i).flag == item.flag) {
                button_add_collect.setText("取消收藏");
                break;
            }
        }
        if(i==ShoppingCartActivity.cartList.size()){
            button_add_collect.setText("收藏");
        }


        button_add_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String name = "收藏";
                if(button_add_collect.getText().toString().equals(name)) {
                    Log.d(TAG, "onClick: czy 收藏");
                    button_add_collect.setText("取消收藏");
                    Toast.makeText(ItemInformation.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    ShoppingCartActivity.cartList.add(item);
                    finish();
                }
                else {
                    Log.d(TAG, "onClick: czy 取消收藏");
                    button_add_collect.setText("收藏");
                    Toast.makeText(ItemInformation.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    ShoppingCartActivity.cartList.remove(i);
                    finish();
                }
            }
        });
    }
}
