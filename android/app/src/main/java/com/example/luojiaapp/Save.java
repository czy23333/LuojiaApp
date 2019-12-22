package com.example.luojiaapp;

import android.content.Context;
import android.content.SharedPreferences;


public class Save {
    public static void saveUserInfo(Context context, String flag, String content) {
        /**
         * SharedPreferences将用户的数据存储到该包下的shared_prefs/config.xml文件中，
         * 并且设置该文件的读取方式为私有，即只有该软件自身可以访问该文件
         */
        SharedPreferences sPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreferences.edit();
        //当然sharepreference会对一些特殊的字符进行转义，使得读取的时候更加准确
        editor.putString(flag, content);
        //切记最后要使用commit方法将数据写入文件
        editor.commit();
    }
}
