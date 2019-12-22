package com.example.luojiaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private int resourceId;

    public ItemAdapter(Context context, int textViewResourceId,
                        List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item Item = getItem(position); // 获取当前项的Item实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ItemImage = (ImageView) view.findViewById (R.id.Item_image);
            viewHolder.ItemName = (TextView) view.findViewById (R.id.Item_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }

        if (Item.getImageId() > 0) {
            viewHolder.ItemImage.setImageResource(Item.getImageId());
        } else {
            viewHolder.ItemImage.setImageBitmap(Item.getBitmap());
        }

        viewHolder.ItemName.setText(Item.getName());
        return view;
    }

    class ViewHolder {

        ImageView ItemImage;

        TextView ItemName;

    }

}
