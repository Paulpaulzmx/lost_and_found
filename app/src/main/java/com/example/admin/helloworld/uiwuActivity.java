package com.example.admin.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.helloworld.fragment.BFragment;
import com.example.admin.helloworld.fragment.CFragment;
import com.example.admin.helloworld.fragment.DFragment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class uiwuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


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
        eDt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(uiwuActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imageView = findViewById(R.id.iv_title_menu1);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(uiwuActivity.this, ContainerActivity.class);
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


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //点击发布按钮，出现对话框，选择拍照或是上传图片
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //新建下拉列表对话框
                AlertDialog.Builder customizeDialog = new AlertDialog.Builder(uiwuActivity.this);
                //自定义对话框样式，暂不需要
//                final View dialogView = LayoutInflater.from(uiwuActivity.this)
//                        .inflate(R.layout.dialog_customize, null);
//                customizeDialog.setTitle("");
//                customizeDialog.setView(dialogView);
                final String[] items = {"拍照", "上传本地图片"};      //列表项
                customizeDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent takePictureIntent = new Intent(
                                        uiwuActivity.this,TakePhotoActivity.class);
                                startActivity(takePictureIntent);
                                break;
                            case 1:
                                //选择本地图片
                                Intent localPictureIntent = new Intent(
                                        uiwuActivity.this, SelectActivity.class);
                                startActivity(localPictureIntent);
                                break;
                            default:
                                break;
                        }
                    }
                });
                customizeDialog.show(); //显示对话框
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toolbar跟menu
        getMenuInflater().inflate(R.menu.rightview_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.actionbar_wode:
                Toast.makeText(getApplicationContext(), "Toast我的发布", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionbar_jmyi:
                Toast.makeText(getApplicationContext(), "Toast建议", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionbar_more:
                Toast.makeText(getApplicationContext(), "Toastgg", Toast.LENGTH_LONG).show();
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

        switch (id) {
            case R.id.pavl:
                Toast.makeText(getApplicationContext(), "拍照", Toast.LENGTH_LONG).show();
                break;
            case R.id.liui:
                Toast.makeText(getApplicationContext(), "历史", Toast.LENGTH_LONG).show();
                break;
            case R.id.uip:
                Toast.makeText(getApplicationContext(), "等带替换1", Toast.LENGTH_LONG).show();
                break;
            case R.id.uevi:
                Toast.makeText(getApplicationContext(), "等待替换2", Toast.LENGTH_LONG).show();
                break;
            case R.id.faxm:
                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_LONG).show();
                break;
            case R.id.gryu:
                Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:
                // 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);

            default:
                break;
        }
    }
}
