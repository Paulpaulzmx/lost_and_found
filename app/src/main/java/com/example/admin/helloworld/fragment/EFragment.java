package com.example.admin.helloworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.helloworld.MyPageAdapter;
import com.example.admin.helloworld.PublishActivity;
import com.example.admin.helloworld.R;

import java.util.ArrayList;

public class EFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_e,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        MyPageAdapter myPageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager());

        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new DFragment());
        datas.add(new AFragment());
        datas.add(new BFragment());
        datas.add(new CFragment());
        myPageAdapter.setData(datas);

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("推荐");
        titles.add("最新");
        titles.add("书籍");
        titles.add("校园卡");

        myPageAdapter.setTitles(titles);


        TabLayout tabLayout = view.findViewById(R.id.tab_gank);
        ViewPager viewPager = view.findViewById(R.id.view_paper);


        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PublishActivity.class);
                startActivity(intent);
            }
        });

    }
}
