package com.example.admin.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {



    private Button mBtnToast1,mBtnToast2,mBtnToast3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        mBtnToast1= findViewById(R.id.btn_toast_1);
        mBtnToast1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_LONG).show();
            }
        });

        mBtnToast2= findViewById(R.id.btn_toast_2);
        mBtnToast2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast toastCenter = Toast.makeText(getApplicationContext(),"juvs",Toast.LENGTH_LONG);
                toastCenter.setGravity(Gravity.CENTER,0,0);
                toastCenter.show();
            }
        });

        mBtnToast3= findViewById(R.id.btn_toast_3);
        mBtnToast3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast toastCustom = new Toast(getApplicationContext());
                LayoutInflater inflater = LayoutInflater.from(ToastActivity.this);
                View view = inflater.inflate(R.layout.layout_toast,null);
                ImageView imageView = view.findViewById(R.id.iv_toast);
                TextView textView = view.findViewById(R.id.tv_toast);
                imageView.setImageResource(R.drawable.ic_launcher_background);
                textView.setText("自定义Toast");
                toastCustom.setView(view);
                toastCustom.show();
            }
        });
    }
}
