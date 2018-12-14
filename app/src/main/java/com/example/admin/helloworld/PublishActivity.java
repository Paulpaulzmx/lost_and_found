package com.example.admin.helloworld;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.helloworld.fragment_lost.aFragment;
import com.example.admin.helloworld.fragment_lost.bFragment;

import java.util.ArrayList;

public class PublishActivity extends AppCompatActivity {

    private ImageView imageView;
    private ArrayList<Fragment>  show_fragment;
    private ArrayList<String> fragment_titles;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        imageView = findViewById(R.id.bt_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        show_fragment = new ArrayList<>();
        show_fragment.add(new aFragment());
        show_fragment.add(new bFragment());

        fragment_titles = new ArrayList<>();
        fragment_titles.add("我丢了东西");
        fragment_titles.add("我捡到东西");

        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        myPageAdapter.setData(show_fragment);
        myPageAdapter.setTitles(fragment_titles);

        tabLayout = findViewById(R.id.tab_gank);
        viewPager = findViewById(R.id.view_paper);

        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
