package com.example.admin.helloworld.Study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.helloworld.Listview.ListViewActivity;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.gridview.GridViewActivity;
import com.example.admin.helloworld.recyclerview.RecyclerViewActivity;
import com.example.admin.helloworld.uiwuActivity;

public class MainActivity extends AppCompatActivity {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton1= findViewById(R.id.btn_1);
        mButton1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,EditTextActivity.class);
                startActivity(intent);
            }
        });
        mButton2 = findViewById(R.id.btn_2);
        mButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });
        mButton3 = findViewById(R.id.btn_3);
        mButton3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);
            }
        });
        mButton4 = findViewById(R.id.btn_4);
        mButton4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });

        mButton5 = findViewById(R.id.btn_5);
        mButton5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });

        mButton6= findViewById(R.id.btn_6);
        mButton6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        mButton7= findViewById(R.id.view_1);
        mButton7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ToastActivity.class);
                startActivity(intent);
            }
        });

        mButton8= findViewById(R.id.view_2);
        mButton8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });

        mButton9= findViewById(R.id.btn_7);
        mButton9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ContainerActivity.class);
                startActivity(intent);
            }
        });

        mButton9= findViewById(R.id.btn_8);
        mButton9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,uiwuActivity.class);
                startActivity(intent);
            }
        });


    }
}
