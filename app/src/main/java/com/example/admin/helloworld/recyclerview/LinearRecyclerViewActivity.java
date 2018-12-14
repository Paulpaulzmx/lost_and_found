package com.example.admin.helloworld.recyclerview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;
import com.example.admin.helloworld.gridview.MyGridViewAdapter;
import com.example.admin.helloworld.gridview.MyGridViewAdapter1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LinearRecyclerViewActivity extends AppCompatActivity {

    private ArrayList arrayList = new ArrayList();
    private RecyclerView mRvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        Inter();
        mRvMain = findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(LinearRecyclerViewActivity.this));

    }

    public void Inter(){
        new Thread(){
            @Override
            public void run(){
                String acceptData = "";
                HttpURLConnection conn=null;
                try {

                    String Strurl="http://47.106.146.182/buy?id=3";
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


    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jsonJXDate(msg.obj.toString(),arrayList);
                    mRvMain.setAdapter(new LinearAdapter(LinearRecyclerViewActivity.this,arrayList));
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
                        arrayList.add(new ShuJu(jsonObject.getString("form"),jsonObject.getString("url"),jsonObject.getString("place"),jsonObject.getString("prices"),jsonObject.getString("market"),jsonObject.getString("number")));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
