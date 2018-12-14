package com.example.admin.helloworld.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;
import com.example.admin.helloworld.jump.AActivity;

import java.util.ArrayList;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {


    private Context mContext;
    private ArrayList arrayList;

    public LinearAdapter(Context context){
        this.mContext = context;
    }

    public LinearAdapter(Context context,ArrayList arrayList){
        this.mContext = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder holder, int position) {

        ShuJu shuJu = (ShuJu) arrayList.get(position);
        Glide.with(mContext).load(shuJu.GetTu()).into(holder.imageView);
        holder.textView.setText(shuJu.GetNa());
        holder.textView1.setText(shuJu.GetAddress());
        holder.textView2.setText(shuJu.GetId());
        holder.textView3.setText("价格："+shuJu.GetTime());
        holder.textView4.setText("数量："+shuJu.GetNumber());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class LinearViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView,textView1,textView2,textView3,textView4;


        public LinearViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_one_title);
            textView1 = itemView.findViewById(R.id.tv_one_directors);
            textView2 = itemView.findViewById(R.id.tv_one_casts);
            textView3 = itemView.findViewById(R.id.tv_one_genres);
            textView4 = itemView.findViewById(R.id.tv_one_rating_rate);
            imageView = itemView.findViewById(R.id.iv_one_photo);
        }
    }
}
