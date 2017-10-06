package com.yhx.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper openHelper = new MyOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createDatabase(View view) {
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
    }


    public void insert(View view) {
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //db.execSQL("insert into person(name,phone,salary) values(?,?,?)",new Object[]{"春晓","138438",13000});
        //db.execSQL("insert into person(name,phone,salary) values(?,?,?)",new Object[]{"春晓的老婆","138438",13000});
        //db.execSQL("insert into person(name,phone,salary) values(?,?,?)",new Object[]{"春晓的闺女","138438",13000});
        ContentValues values = new ContentValues();
        values.put("name","杨松");
        values.put("phone","1388888");
        values.put("salary",13201);
        long l = db.insert("person",null,values);
        System.out.println(l);
        db.close();
    }

    public void delete(View view) {
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //db.execSQL("delete from person where name = ?",new Object[]{"春晓的闺女"});
        int i = db.delete("person","_id = ?",new String[]{"4"});
        System.out.println(i);
        db.close();
    }

    public void update(View view) {
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //db.execSQL("update person set salary = ? where name = ?",new Object[]{"13200","春晓"});
        ContentValues values = new ContentValues();
        values.put("name","杨松的爱妾");
        int i = db.update("person",values,"_id = ?",new String[]{"3"});
        System.out.println(i);
        db.close();
    }

    public void select(View view) {
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //Cursor cursor = db.rawQuery("select * from person",null);
        //arg1：查询的字段
        //arg2：查询的where条件
        //arg3：where条件的占位符
        Cursor cursor = db.query("person",null,null,null,null,null,null);
        //把指针移动到下一行
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int salary = cursor.getInt(cursor.getColumnIndex("salary"));
            System.out.println(name+","+phone+","+salary);
        }
        db.close();
    }

    public void transaction(View view){
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
        try{
            //开启事务
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("salary",13199);
            db.update("person",values,"name = ?",new String[]{"杨松"});
            //清空values的内容
            values.clear();
            values.put("salary",13202);
            db.update("person",values,"name = ?",new String[]{"春晓"});

            //设置事务执行成功，提交时这行代码没有执行过，就会回滚
            db.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //关闭事务，提交数据
            db.endTransaction();
        }
    }
}
