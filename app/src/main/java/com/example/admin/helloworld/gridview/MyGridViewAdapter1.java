package com.example.admin.helloworld.gridview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;
import com.example.admin.helloworld.fragment_course.ShopActivity;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MyGridViewAdapter1 extends BaseAdapter implements AdapterView.OnItemClickListener {

    private ArrayList arrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyGridViewAdapter1(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public MyGridViewAdapter1(Context context,ArrayList arrayList){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, ShopActivity.class);
        ShuJu shuJu = (ShuJu) arrayList.get(position);
        intent.putExtra("url",shuJu.GetTu());
        intent.putExtra("place",shuJu.GetNa());
        intent.putExtra("market",shuJu.GetAddress());
        intent.putExtra("prices",shuJu.GetTime());
        intent.putExtra("form",shuJu.GetId());
        mContext.startActivity(intent);
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textView,textView1,textView2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.layout_grid_item,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv_gridv);
            holder.textView = convertView.findViewById(R.id.iv_title);
            holder.textView1 = convertView.findViewById(R.id.iv_title1);
            holder.textView2 = convertView.findViewById(R.id.et_type_prices);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();

        }
        //holder.textView.setText("花啊花啊");
        holder.imageView.setTag(R.id.tag_glide,position);
        ShuJu shuJu = (ShuJu) arrayList.get(position);
        holder.textView.setText(shuJu.GetNa());
        holder.textView1.setText(shuJu.GetAddress());
        holder.textView2.setText(shuJu.GetTime());
        Glide.with(mContext).load(shuJu.GetTu()).bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL)).into(holder.imageView);
        ((GridView)parent).setOnItemClickListener(this);

        return convertView;
    }
}
