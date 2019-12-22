package com.example.luojiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SellerActivity extends AppCompatActivity {

    private List<Item> ItemList = new ArrayList<Item>();
    private String name;
    private String phone;

    private static final String TAG = "SellerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        ItemList.addAll(temp_item_array.ItemList);
        ServerConnection.getSellerItems(ItemList);  // 从服务器获取商品

        Button back_to_center=(Button)findViewById(R.id.person_center);
        Button back_to_origin=(Button)findViewById(R.id.back2);
        Button add_selling = (Button)findViewById(R.id.add_selling);
        back_to_center.setOnClickListener(new View.OnClickListener() {//点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerActivity.this,PersonalCenterActivity.class);
                startActivity(intent);
            }
        });
        back_to_origin.setOnClickListener(new View.OnClickListener() {//点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        add_selling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerActivity.this, AddSell.class);
                startActivity(intent);
            }
        });


        final List<TextView> infos = new ArrayList<>();
        initInfos(infos);

        Button selling = (Button)findViewById(R.id.selling);
        final Button seller_information = (Button) findViewById(R.id.seller_information);


        ItemAdapter adapter = new ItemAdapter(SellerActivity.this, R.layout.item, ItemList);
        final ListView listView = (ListView) findViewById(R.id.list_view_seller);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ItemInformation.item = ItemList.get(position);  // 设置要显示的 item
                startActivity(new Intent(SellerActivity.this, ItemInformation.class));
            }
        });

        seller_information.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (TextView t : infos) {
                    t.setVisibility(View.VISIBLE);
                }
                listView.setVisibility(View.GONE);
            }
        });

        selling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TextView t : infos) {
                    t.setVisibility(View.GONE);
                }
                listView.setVisibility(View.VISIBLE);
            }
        });
    }

    // 从登陆信息初始化商人信息的控件列表
    private void initInfos(final List<TextView> infos) {
        infos.add((TextView)findViewById(R.id.SellerName));
        infos.add((TextView)findViewById(R.id.SellerPhone));
        infos.add((TextView)findViewById(R.id.Department));
        infos.add((TextView)findViewById(R.id.School));
        infos.add((TextView)findViewById(R.id.Grade));
        infos.add((TextView)findViewById(R.id.seller_name));
        infos.get(infos.size() - 1).setText(LoginStatus.realname);
        infos.add((TextView)findViewById(R.id.seller_phone));
        infos.get(infos.size() - 1).setText(LoginStatus.contact);
        infos.add((TextView)findViewById(R.id.department));
        infos.get(infos.size() - 1).setText(LoginStatus.department);
        infos.add((TextView)findViewById(R.id.school));
        infos.get(infos.size() - 1).setText(LoginStatus.school);
        infos.add((TextView)findViewById(R.id.grade));
        infos.get(infos.size() - 1).setText(LoginStatus.grade);
    }
}
