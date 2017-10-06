package com.yhx.showdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/24.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase db){
        System.out.println("创建数据库");
        db.execSQL("create table person(_id integer primary key autoincrement,name char(10),phone char(20),salary integer(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        System.out.println("升级数据库");
    }

    public MyOpenHelper(Context context) {
        super(context, "person.db",null,1);
    }
}
