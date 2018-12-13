package com.example.admin.helloworld;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class SearchActivity extends AppCompatActivity {


    private TextView tv_search;
    private TextView tv_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tv_cancel=(TextView)findViewById(R.id.tv_cancel);
        tv_search=(TextView)findViewById(R.id.tv_search);
        Drawable drawable=getResources().getDrawable(R.drawable.search_sign);
        drawable.setBounds(40,0,100,60);
        tv_search.setCompoundDrawables(drawable,null,null,null);
    }
    public void click1(View v){
        onBackPressed();
    }

}
