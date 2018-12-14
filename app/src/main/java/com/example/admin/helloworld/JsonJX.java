package com.example.admin.helloworld;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonJX {


    public static String jsonJXDate(String date) {
        String name = null;
        if(date!=null) {
            try {
                JSONObject jsonObject = new JSONObject(date);
                String resultCode = jsonObject.getString("message");
                if (resultCode.equals("sucessed 02 ")) {
                    JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<resultJsonArray.length();i++){
                        jsonObject = resultJsonArray.getJSONObject( i );
                        name = jsonObject.getString("user_phone");
                        System.out.println(name+"\nhello world  ");
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
        return name;
    }


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
                        arrayList.add(new ShuJu(jsonObject.getString("user_id"),jsonObject.getString("loss_image"),jsonObject.getString("loss_name"),jsonObject.getString("loss_time"),jsonObject.getString("loss_address"),jsonObject.getString("loss_phone")));

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
