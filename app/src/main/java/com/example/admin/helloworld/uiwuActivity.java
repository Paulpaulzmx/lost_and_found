package com.example.admin.helloworld;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.admin.helloworld.Study.ContainerActivity;
import com.example.admin.helloworld.Study.EditTextActivity;
import com.example.admin.helloworld.Study.WebViewActivity;
import com.example.admin.helloworld.fragment.BFragment;
import com.example.admin.helloworld.fragment.CFragment;
import com.example.admin.helloworld.fragment.DFragment;
import com.example.admin.helloworld.statusbar.StatusBarUtil;
import com.example.admin.helloworld.util.GlideCircleTransform;

import java.util.ArrayList;

public class uiwuActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {



    private TabLayout tabLayout;
    private TextView eDt;
    private ImageView imageView;
    private ImageView imageView1;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiwu);


        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager());

        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new DFragment());
        datas.add(new Fragment());
        datas.add(new BFragment());
        datas.add(new CFragment());
        myPageAdapter.setData(datas);

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("推荐");
        titles.add("最新");
        titles.add("书籍");
        titles.add("校园卡");

        myPageAdapter.setTitles(titles);


        TabLayout tabLayout = findViewById(R.id.tab_gank);
        ViewPager viewPager = findViewById(R.id.view_paper);


        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);




        eDt = findViewById(R.id.edt_search);
        eDt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(uiwuActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });


        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imageView = findViewById(R.id.iv_title_menu1);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(uiwuActivity.this,ContainerActivity.class);
                startActivity(intent);
            }
        });


        drawerLayout = findViewById(R.id.drawer_layout);
        imageView1 = findViewById(R.id.iv_title_menu);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        StatusBarUtil.setColorForDrawerLayout(uiwuActivity.this, drawerLayout, 0, 0);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateHeaderView(R.layout.nav_header_drawer);
        View headerView = navigationView.getHeaderView(0);
        ImageView avatar = headerView.findViewById(R.id.iv_avatar);
        headerView.findViewById(R.id.ll_nav_mess).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_scan_history).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_found).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_about).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_game).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_near).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_my).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_friends).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_collect).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_dark).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_set).setOnClickListener(this);
        headerView.findViewById(R.id.ll_nav_exit).setOnClickListener(this);
        Glide.with(getApplication()).load("http://47.106.146.182/pictures/profileicon603.jpg").transform(new GlideCircleTransform(avatar.getContext())).into(avatar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(uiwuActivity.this,EditTextActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toobar跟menu
        getMenuInflater().inflate(R.menu.rightview_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.actionbar_wode:
                Toast.makeText(getApplicationContext(),"Toast我的发布",Toast.LENGTH_LONG).show();
                break;
            case R.id.actionbar_jmyi:
                Toast.makeText(getApplicationContext(),"Toast建议",Toast.LENGTH_LONG).show();
                break;
            case R.id.actionbar_more:
                Toast.makeText(getApplicationContext(),"Toastgg",Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
         drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.pavl:Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_LONG).show();
                break;
            case R.id.liui:Toast.makeText(getApplicationContext(),"历史",Toast.LENGTH_LONG).show();
                break;
            case R.id.uip:Toast.makeText(getApplicationContext(),"等带替换1",Toast.LENGTH_LONG).show();
                break;
            case R.id.uevi:Toast.makeText(getApplicationContext(),"等待替换2",Toast.LENGTH_LONG).show();
                break;
            case R.id.faxm:Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_LONG).show();
                break;
            case R.id.gryu:Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_LONG).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_nav_mess:
                Intent intent = new Intent(uiwuActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.ll_nav_exit:
                // 退出应用
                finish();
                break;

            default:
                break;
        }
    }



}
