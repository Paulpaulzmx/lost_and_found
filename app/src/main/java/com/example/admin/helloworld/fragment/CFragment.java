package com.example.admin.helloworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.helloworld.Main2Activity;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.Study.WebViewActivity;
import com.example.admin.helloworld.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class CFragment extends Fragment implements OnBannerListener {

    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = view.findViewById(R.id.banner);
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("https://i0.hdslb.com/bfs/archive/caf751c95b1c1cdb59acec0b5f6eb9cf0e79f0a4.jpg");
        list_path.add("https://i0.hdslb.com/bfs/live/799c9df56d04f9efe74e1f4e6bb74c514db61491.jpg");
        list_path.add("https://i0.hdslb.com/bfs/bangumi/ac098b026601ee5cc642263dceeb531254556c19.jpg");
        list_path.add("https://i0.hdslb.com/bfs/sycp/creative_img/201812/f96331bde003af08b631419aaf40fadf.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("勤劳可爱");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        Intent intent;
        //Toast.makeText(getActivity(),"hello 你好啊！！"+position,Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://live.bilibili.com/2233?spm_id_from=333.334.b_63686965665f7265636f6d6d656e64.7");
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://www.bilibili.com/blackboard/activity-pokemonlglive.html?spm_id_from=333.334.b_62696c695f6c697665.28");
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://www.bilibili.com/bangumi/play/ss25469/?spm_id_from=333.334.b_72616e6b696e675f74696d696e675f67756f636875616e67.8");
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://search.bilibili.com/video?keyword=%E5%A4%A9%E5%93%A5%E5%9C%A8%E5%A5%94%E8%B7%91");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
