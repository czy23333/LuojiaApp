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

import com.example.luojiaapp.LoginStatus;

public class MainActivity extends AppCompatActivity {

    private List<Item> ItemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button_me = (Button)findViewById(R.id.title_me);
        final Button button_login=(Button)findViewById(R.id.title_login);//和登录绑定
        Button button_search=(Button)findViewById(R.id.search_button);//查找商品的按钮

        if (LoginStatus.loggedin) {
            button_login.setText("注销");
        }
        else {
            button_login.setText("登陆");
        }

        final EditText search_name=(EditText)findViewById(R.id.search);//存储商品名字
        ItemList.addAll(temp_item_array.ItemList);
        ServerConnection.getRecommendedItems(ItemList); // 初始化商品数据
        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item, ItemList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ItemInformation.item = ItemList.get(position);  // 设置要显示的 item
                startActivity(new Intent(MainActivity.this, ItemInformation.class));
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {//点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                if (LoginStatus.loggedin) {
                    LoginStatus.loggedin = false;
                    button_login.setText("登陆");
                }
                else {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        button_search.setOnClickListener(new View.OnClickListener() {//点击登录按钮触发效果
            @Override
            public void onClick(View v) {
                final String item_name= search_name.getText().toString();
                Toast.makeText(MainActivity.this, item_name, Toast.LENGTH_SHORT).show();
            }
        });
        button_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginStatus.loggedin) {
                    startActivity(new Intent(MainActivity.this, PersonalCenterActivity.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "请先登陆", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
