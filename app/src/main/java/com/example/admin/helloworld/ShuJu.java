package com.example.admin.helloworld;

public class ShuJu {
    private String picture;
    private String name;
    private String time;
    private String address;

    public ShuJu(String picture, String name, String time, String address) {
        this.picture = picture;
        this.name = name;
        this.time = time;
        this.address = address;
    }

    public void SetPicture(String picture) { this.picture = picture;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetTime(String time) {
        this.time = time;
    }

    public void SetAddress(String address) {
        this.address = address;
    }

    public String GetPicture() {
        return this.picture;
    }

    public String GetName() {
        return this.name;
    }

    public String GetTime() {
        return this.time;
    }

    public String GetAddress() {
        return this.address;
    }

}
