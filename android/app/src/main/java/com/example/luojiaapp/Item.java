package com.example.luojiaapp;

import android.graphics.Bitmap;

public class Item {
    public static int flag_current = 0;
    public int flag;
    private String name;
    private int imageId;
    private int imageId2;
    private Bitmap bitmap;
    private int price;
    private int amount;
    private int category;

    final static int book = 1;
    final static int electric = 2;
    final static int life = 3;
    final static int other = 0;

    public Item(String name, int price, int amount, int category, Bitmap bitmap) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.bitmap = bitmap;
    }

    public Item(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
        imageId2 = R.drawable.saber;
        this.price = -1;
        this.amount = -1;
        this.category = other;
    }

    public Item(String name, int imageId, int imageId2, int price, int amount, int category) {
        this.name = name;
        this.imageId = imageId;
        this.imageId2 = imageId2;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
    public int getImageId2() {return imageId2;}
    public int getPrice(){return price;}
    public int getAmount(){return amount;}
    public int getCategory() {return category;}
    public Bitmap getBitmap() {return bitmap;}

    public void setName(String name){this.name = name;}
    public void setPrice(int price) {this.price = price;}
    public void setAmount(int amount) {this.amount = amount;}
    public void setCategory(int category) {this.category = category;}
    public void setImageId(int imageId) {this.imageId = imageId;}
    public void setImageId2(int imageId2) {this.imageId2 = imageId2;}
    public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}

}
