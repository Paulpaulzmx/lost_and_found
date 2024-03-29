package com.example.admin.helloworld;

public class ShuJu {
    private String id;
    private String tupm;
    private String name;
    private String time;
    private String address;
    private String number;

    public ShuJu(String id,String tupm, String name, String time, String address){
        this.id=id;
        this.tupm=tupm;
        this.name=name;
        this.time=time;
        this.address=address;
    }

    public ShuJu(String id,String tupm, String name, String time, String address,String number){
        this.id=id;
        this.tupm=tupm;
        this.name=name;
        this.time=time;
        this.address=address;
        this.number=number;
    }
    public void SetId(String id){
        this.id=id;
    }

    public void SetTu(String tupm){
        this.tupm=tupm;
    }

    public void SetNa(String name){
        this.name=name;
    }

    public void SetTime(String time){
        this.time=time;
    }

    public void SetAddress(String address){
        this.address=address;
    }

    public void SetNumber(String number){
        this.number=number;
    }

    public String GetId(){
        return this.id;
    }

    public String GetTu(){
        return this.tupm;
    }

    public String GetNa(){
        return this.name;
    }

    public String GetTime(){
        return this.time;
    }

    public String GetAddress(){
        return this.address;
    }

    public String GetNumber(){
        return this.number;
    }

}
