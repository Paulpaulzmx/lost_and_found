package com.example.admin.helloworld.fragment_course;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.admin.helloworld.JsonJX;
import com.example.admin.helloworld.Listview.MyListAdapter;
import com.example.admin.helloworld.PublishActivity;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;
import com.example.admin.helloworld.Study.WebViewActivity;
import com.example.admin.helloworld.gridview.GridViewActivity;
import com.example.admin.helloworld.gridview.MyGridViewAdapter;
import com.example.admin.helloworld.gridview.MyGridViewAdapter1;
import com.example.admin.helloworld.recyclerview.LinearRecyclerViewActivity;
import com.example.admin.helloworld.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class fFragment extends Fragment implements OnBannerListener {

    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private GridView mGv;
    private GridView mGv1;
    private ArrayList<Object> arrayList = new ArrayList<>();
    private ArrayList<Object> arrayList1 = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.header_item_everyday,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        mGv = view.findViewById(R.id.gv);
        mGv1 = view.findViewById(R.id.gv1);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LinearRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        Inter();
        Inter1();
    }

    public void init(View view){
        banner = view.findViewById(R.id.banner);
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("https://img.alicdn.com/tfs/TB1DK2UuVzqK1RjSZSgXXcpAVXa-520-280.jpg_q90_.webp");
        list_path.add("https://img.alicdn.com/simba/img/TB1_MwMuW6qK1RjSZFmSut0PFXa.jpg");
        list_path.add("https://img.alicdn.com/tfs/TB1mZqJurvpK1RjSZFqXXcXUVXa-520-280.jpg_q90_.webp");
        list_path.add("https://img.alicdn.com/simba/img/TB1JVk.u4YaK1RjSZFnSuu80pXa.jpg");
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

    public void OnBannerClick(int position) {
        Intent intent;
        //Toast.makeText(getActivity(),"hello 你好啊！！"+position,Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://market.m.taobao.com/apps/abs/10/401/f501b0?spm=a21bo.2017.201862-1.d1.5af911d94dw8co&wh_weex=true&wx_navbar_transparent=true&data_prefetch=true&pos=1&psId=1702003&acm=20140506001.1003.2.4840892&scm=1003.2.20140506001.OTHER_1546360211652_4840892");
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://click.mz.simba.taobao.com/ecpm?spm=a21bo.2017.201862-3.1&e=2%2FVYyrYUsKf2Gv8a%2BYSvrFnSehBAGPm8KSva0RotDY3GDpN%2FNTnjYvzjXNR3i3VFyH0mcIfr6GGUHNSS4U4ao77ToGykAbj0tbR9sxq9q4hbP149m5qBPAUxj5B42Iyfvw714PHUEvcG5cL59LdYtIXeWLk8lKX20Rh14EA%2FFNQ%2Fv8dpNcZTFr%2Fz7RlhP8J8NOhqpE%2Fda26RMWwdzJBcVLh6QnOHJIG1Ij2LH9XQzV7O%2BIm3%2FW51hJ4fKZROa7Hv1QbqmFDY9YNhMTTqAOzTDBQpD4K75EPats%2BkWAYCnVUP1eBec0uzxO2EBsKK8c4BhBM9z1D6l1eg5Olw83Rjvs1HAAK2Dqpt6kve3bnRVpV9lirfAE8qScIvXI5kohlrOlz%2BcNqT%2BlyQh2WnuIYoPeT4pI4QxGqbXjt%2BSpjB0ZOWQ%2BQ8Dr%2FBDyaaCfHo1KOexK87hQU8GCglRmK23scDjksucyp80%2BwAAEtldp2lWF8%3D&u=https%3A%2F%2Fdetail.tmall.com%2Fitem.htm%3Fid%3D567463578830%26sku_properties%3D1627207%3A28341%26scene%3Dtaobao_shop&k=493");
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://meizu.tmall.com/?ali_trackid=17_c9f8458114b0bbdcb17d9ba99f31afc6&spm=a21bo.2017.201862-4.1");
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("URL","https://pages.tmall.com/wow/a/act/21024/wupr?spm=a21bo.2017.201862-5.d1.5af911d94dw8co&pos=1&wh_pid=industry-154794&acm=20140506002.1003.2.4825350&scm=1003.2.20140506002.OTHER_1546274731484_4825350");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void Inter(){
        new Thread(){
            @Override
            public void run(){
                String acceptData = "";
                HttpURLConnection conn=null;
                try {

                    String Strurl="http://47.106.146.182/buy?id=1";
                    URL url = new URL(Strurl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
                        Log.i("PostGetUtil","get请求成功");
                        InputStream in=conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                        String line;
                        while((line=bufferedReader.readLine())!=null){ //不为空进行操作
                            acceptData+=line;
                        }
                        System.out.println("接受到的数据："+acceptData);
                        in.close();
                    }
                    else {
                        Log.i("PostGetUtil","get请求失败");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    conn.disconnect();
                }
                Message msg=new Message();
                msg.obj=acceptData;
                msg.what=1;
                handler.sendMessage(msg);
            }
        }.start();
    }

    public void Inter1(){
        new Thread(){
            @Override
            public void run(){
                String acceptData = "";
                HttpURLConnection conn=null;
                try {

                    String Strurl="http://47.106.146.182/buy?id=2";
                    URL url = new URL(Strurl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
                        Log.i("PostGetUtil","get请求成功");
                        InputStream in=conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                        String line;
                        while((line=bufferedReader.readLine())!=null){ //不为空进行操作
                            acceptData+=line;
                        }
                        System.out.println("接受到的数据："+acceptData);
                        in.close();
                    }
                    else {
                        Log.i("PostGetUtil","get请求失败");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    conn.disconnect();
                }
                Message msg1=new Message();
                msg1.obj=acceptData;
                msg1.what=2;
                handler.sendMessage(msg1);
            }
        }.start();
    }

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jsonJXDate(msg.obj.toString(),arrayList);
                    mGv.setAdapter(new MyGridViewAdapter(getActivity(),arrayList));
                    break;
                case 2:
                    jsonJXDate(msg.obj.toString(),arrayList1);
                    mGv1.setAdapter(new MyGridViewAdapter1(getActivity(),arrayList1));
                    break;
            }
        }
    };

    public static void jsonJXDate(String date, ArrayList<Object> arrayList) {
        if(date!=null) {
            arrayList.clear();
            try {
                JSONObject jsonObject = new JSONObject(date);
                String resultCode = jsonObject.getString("message");
                if (resultCode.equals("sucessed 01 ")) {
                    JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<resultJsonArray.length();i++){
                        jsonObject = resultJsonArray.getJSONObject( i );
                        arrayList.add(new ShuJu(jsonObject.getString("form"),jsonObject.getString("url"),jsonObject.getString("place"),jsonObject.getString("prices"),jsonObject.getString("market")));

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
