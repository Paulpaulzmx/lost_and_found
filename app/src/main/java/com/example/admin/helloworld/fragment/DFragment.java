package com.example.admin.helloworld.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.helloworld.JsonJX;
import com.example.admin.helloworld.Listview.MyListAdapter;
import com.example.admin.helloworld.PostGetUtil;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.ShuJu;

import java.util.ArrayList;

public class DFragment extends Fragment {

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
                    mL1.setAdapter(new MyListAdapter(getActivity(),arrayList));



            }
        }
    };












}
