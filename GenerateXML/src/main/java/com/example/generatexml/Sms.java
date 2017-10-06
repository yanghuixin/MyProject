package com.example.generatexml;

/**
 * Created by Administrator on 2017/9/12.
 */

public class Sms {
    private String body;
    private long date;
    private int type;
    private String address;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Sms(String body,long date, int type,String address) {
        super();
        this.body = body;
        this.date = date;
        this.type = type;
        this.address = address;
    }
}
