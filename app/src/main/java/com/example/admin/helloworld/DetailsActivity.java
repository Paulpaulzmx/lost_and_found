package com.example.admin.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity
        implements View.OnClickListener {

    private String id;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //通过findViewById初始化控件
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView_back = (ImageView) findViewById(R.id.details_back);
        FloatingActionButton fab_call = (FloatingActionButton) findViewById(R.id.fab_call);

        Intent intent = getIntent();

        TextView textViewname = findViewById(R.id.name);
        textViewname.setText(intent.getStringExtra("name"));

        TextView textViewtime = findViewById(R.id.time);
        textViewtime.setText(intent.getStringExtra("time"));

        TextView textViewaddress = findViewById(R.id.address);
        textViewaddress.setText(intent.getStringExtra("address"));

        TextView textViewphone = findViewById(R.id.other);
        textViewphone.setText("电话号码    "+intent.getStringExtra("phone"));

        phone = intent.getStringExtra("phone");
        id = intent.getStringExtra("id");

        //从网上获取图片
        Glide.with(getApplication()).
                load(intent.getStringExtra("image")).
                into(imageView);

        Inter();

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
                call(phone);
                break;

            default:

        }

    }

    //打电话，接受一个电话号码参数
    public void call(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void Inter() {
        new Thread() {
            public void run() {
                String date = PostGetUtil.SendPostRequest("id=2&name="+id);
                Message msg = new Message();
                msg.obj = date;
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }.start();
    }


    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    TextView textViewPhone = findViewById(R.id.other);
                    //phone = JsonJX.jsonJXDate(msg.obj.toString());
                    //textViewPhone.setText("电话号码    "+phone);
                    //Toast.makeText(getContext(),"Toast我的发布"+msg.obj.toString(),Toast.LENGTH_LONG).show();
            }
        }
    };
}
