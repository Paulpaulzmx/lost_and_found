package com.example.admin.helloworld.Listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.admin.helloworld.R;

public class ListViewActivity extends Activity {


    private ListView mLv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mLv1 = findViewById(R.id.Lv_1);
        mLv1.setAdapter(new MyListAdapter(ListViewActivity.this));
    }
}
