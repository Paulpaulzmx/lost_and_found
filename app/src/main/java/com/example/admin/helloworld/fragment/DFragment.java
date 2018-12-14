package com.example.admin.helloworld.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.helloworld.JsonJX;
import com.example.admin.helloworld.Listview.MyListAdapter;
import com.example.admin.helloworld.PostGetUtil;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;
import com.example.admin.helloworld.View.RefreshLayout;

import java.util.ArrayList;

public class DFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener {

    private MyListAdapter myListAdapter;
    private RefreshLayout refreshLayout;
    private ListView mL1;
    private ArrayList<Object> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_listview,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mL1 = view.findViewById(R.id.Lv_1);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        Inter();
        refresh();
    }


    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    JsonJX.jsonJXDate(msg.obj.toString(),arrayList);
                    //Toast.makeText(getContext(),"Toast我的发布"+msg.obj.toString(),Toast.LENGTH_LONG).show();
                    System.out.println(arrayList.size());
                    ShuJu shuJu = (ShuJu) arrayList.get(0);
                    System.out.println(shuJu.GetNa());
                    myListAdapter = new MyListAdapter(getActivity(),arrayList);
                    mL1.setAdapter(myListAdapter);
                    break;

            }
        }
    };


    private void refresh() {
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDark, R.color.colorPrimary);
//下拉刷新
        refreshLayout.setOnRefreshListener(this);
//上拉加载
        refreshLayout.setOnLoadListener(this);
    }


    @Override
    public void onRefresh() {
        Inter();
        refreshLayout.setRefreshing(false);

    }


    @Override
    public void onLoad() {
        arrayList.add(new ShuJu("1","http://img.zcool.cn/community/01fbe655dfe31932f875a1328a500b.jpg@900w_1l_2o_100sh.jpg","Goole","2018-10-03","修思屯"));
        myListAdapter.notifyDataSetChanged();
        refreshLayout.setLoading(false);

    }

    @Override
    public void dismiss() {
        getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
    }

    @Override
    public void appear() {
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
    }


    public void Inter(){
        new Thread(){
            public void run(){
                String date = PostGetUtil.SendPostRequest("id=1");
                Message msg=new Message();
                msg.obj=date;
                msg.what=1;
                handler.sendMessage(msg);
            }
        }.start();
    }











}
