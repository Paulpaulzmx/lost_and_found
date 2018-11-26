package com.example.admin.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //通过findViewById初始化控件
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView_back = (ImageView) findViewById(R.id.details_back);
        FloatingActionButton fab_call = (FloatingActionButton) findViewById(R.id.fab_call);

        //从网上获取图片
        Glide.with(getApplication()).
                load("http://47.106.146.182/pictures/profileicon605.jpg").
                into(imageView);

        //返回icon和打电话浮动按钮注册监听
        imageView_back.setOnClickListener(this);
        fab_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.details_back:        //单击返回icon回到上一级
                onBackPressed();
                break;

            case R.id.fab_call:            //点击按钮拨打发布者电话
                call(10086);
                break;

            default:

        }

    }

    //打电话，接受一个电话号码参数
    public void call(int phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}
