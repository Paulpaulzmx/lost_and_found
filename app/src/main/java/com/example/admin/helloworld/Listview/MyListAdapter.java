package com.example.admin.helloworld.Listview;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.helloworld.DetailsActivity;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;
import com.example.admin.helloworld.fragment.DFragment;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Object> arrayList;


    public MyListAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public MyListAdapter(Context context, ArrayList<Object> arrayList) {
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
        Intent intent = new Intent(mContext,DetailsActivity.class);
        mContext.startActivity(intent);

    }


    static class ViewHoIder {
        public ImageView imageView;
        public TextView tvTile, tvTime, tvContent;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoIder hoIder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.card_view, null);
            hoIder = new ViewHoIder();
            hoIder.imageView = convertView.findViewById(R.id.iv);
            hoIder.tvContent = convertView.findViewById(R.id.tv_content);
            hoIder.tvTile = convertView.findViewById(R.id.tv_title);
            hoIder.tvTime = convertView.findViewById(R.id.tv_time);
            convertView.setTag(hoIder);
        } else {
            hoIder = (ViewHoIder) convertView.getTag();
        }

        ShuJu shuJu = (ShuJu) arrayList.get(position);
        hoIder.imageView.setTag(R.id.tag_glide, position);
        Glide.with(mContext).load(shuJu.GetTu()).into(hoIder.imageView);
        hoIder.tvTile.setText("这是标题恩 " + shuJu.GetNa());
        hoIder.tvTime.setText(shuJu.GetTime() + " 第" + position + "条恩");
        hoIder.tvContent.setText("这是内容oa: " + shuJu.GetAddress());
        ((ListView) parent).setOnItemClickListener(this);


        return convertView;
    }
}
