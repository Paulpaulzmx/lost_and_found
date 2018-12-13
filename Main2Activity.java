package com.example.admin.helloworld;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.helloworld.Study.EditTextActivity;
import com.example.admin.helloworld.Study.WebViewActivity;
import com.example.admin.helloworld.View.BottomBar;
import com.example.admin.helloworld.fragment.BFragment;
import com.example.admin.helloworld.fragment.CFragment;
import com.example.admin.helloworld.fragment.DFragment;
import com.example.admin.helloworld.fragment.EFragment;
import com.example.admin.helloworld.fragment_course.fFragment;
import com.example.admin.helloworld.statusbar.StatusBarUtil;
import com.example.admin.helloworld.util.GlideCircleTransform;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private PopupWindow popupWindow;
    private TextView eDt;
    private ImageView imageView;
    private ImageView imageView1;
    private DrawerLayout drawerLayout;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar =  findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        initBottom();
        init();

    }


    public void initBottom(){
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setToolbar(toolbar)
                .setActivity(Main2Activity.this)
                .setDrawerLayout(drawerLayout)
                .setTitleBeforeAndAfterColor("#585858", "#1B82D2")
                .setIconHeight(26).setIconWidth(26)
                .setTitleIconMargin(0)
                .addItem(EFragment.class,
                        "首页",
                        R.drawable.home,
                        R.drawable.home_fill)
                .addItem(fFragment.class,
                        "频道",
                        R.drawable.manage,
                        R.drawable.manage_fill)
                .addItem(CFragment.class,
                        "动态",
                        R.drawable.wangwang,
                        R.drawable.wangwang_fill)
                .addItem(DFragment.class,
                        "应用",
                        R.drawable.select,
                        R.drawable.select_fill)
                .build();
    }

    public void init(){



        eDt = findViewById(R.id.edt_search);
        eDt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }


        imageView = findViewById(R.id.iv_title_menu1);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                View view = getLayoutInflater().inflate(R.layout.popup_window,null);
                view.findViewById(R.id.ll_popup_add_friends).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent(Main2Activity.this,EditTextActivity.class);
                        startActivity(intent);
                    }
                });
                view.findViewById(R.id.ll_popup_more).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent(Main2Activity.this,WebViewActivity.class);
                        intent.putExtra("URL","https://m.v.qq.com");
                        startActivity(intent);
                    }
                });
                view.findViewById(R.id.ll_popup_scan).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                view.findViewById(R.id.ll_popup_suggestion).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(imageView,0,20);
            }
        });

        imageView1 = findViewById(R.id.iv_title_menu);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        StatusBarUtil.setColorForDrawerLayout(Main2Activity.this, drawerLayout, 0, 0);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateHeaderView(R.layout.nav_header_drawer);
        View headerView = navigationView.getHeaderView(0);
        ImageView avatar = headerView.findViewById(R.id.iv_avatar);
        avatar.setOnClickListener(this);
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

    }


    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - exitTime) < 2000) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = currentTime;
            }
        }
    }


    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_avatar:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","https://m.bilibili.com");
                startActivity(intent);
                break;
            case R.id.ll_nav_mess:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","https://m.baidu.com");
                startActivity(intent);
                break;
            case R.id.ll_nav_scan_history:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","http://m.imomoe.net");
                startActivity(intent);
                break;
            case R.id.ll_nav_found:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","http://m.dilidili.wang");
                startActivity(intent);
                break;
            case R.id.ll_nav_exit:
                // 退出应用
                finish();
                break;
            case R.id.ll_nav_about:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","http://47.106.146.182");
                startActivity(intent);
                break;
            case R.id.ll_nav_game:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","http://h.4399.com");
                startActivity(intent);
                break;
            case R.id.ll_nav_near:
                intent = new Intent(Main2Activity.this,WebViewActivity.class);
                intent.putExtra("URL","http://www.51cto.com");
                startActivity(intent);
                break;
            case R.id.ll_nav_my:
                intent = new Intent(Main2Activity.this,EditTextActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
