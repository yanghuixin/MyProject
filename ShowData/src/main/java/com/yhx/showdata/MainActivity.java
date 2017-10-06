package com.yhx.showdata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yhx.showdata.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper openHelper = new MyOpenHelper(this);
        //如果数据库不存在，先创建后打开，如果存在，直接打开
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //创建50条数据
        /*ContentValues values = new ContentValues();
        for (int i = 0 ; i < 50 ; i++){
            values.clear();
            values.put("name","张" + i);
            values.put("phone","138" + i);
            values.put("salary","200" + i);
            db.insert("person",null,values);
        }*/
        Cursor cursor = db.query("person",null,null,null,null,null,null);
        personList = new ArrayList<Person>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int salary = cursor.getInt(cursor.getColumnIndex("salary"));
            Person person = new Person(name,phone,salary);
            personList.add(person);
        }

        /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        for (Person person:personList){
            //创建文本框
            TextView textView = new TextView(this);
            textView.setText(person.toString());
            textView.setTextSize(15);
            //把文本框设置为线性布局的子节点
            linearLayout.addView(textView);
        }*/

        //找到listView
        ListView listView = (ListView) findViewById(R.id.list_item);
        //设置显示内容
        listView.setAdapter(new MyAdapter());
        db.close();
    }

    class MyAdapter extends BaseAdapter{

        //系统调用，用来获知模型层有多少条数据
        @Override
        public int getCount() {
            return personList.size();
        }

        //系统调用，返回的view对象就会作为ListView的一个条目显示在屏幕上
        //position：该次getView调用返回的view对象在ListView中是第几个条目，position的值就是几
        //convertView：系统之前缓存的条目
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("getView方法调用"+position);
            Person person = personList.get(position);
            /*TextView textView = new TextView(MainActivity.this);
            textView.setText(person.toString());
            textView.setTextSize(20);*/
            //把布局文件填充成view对象
            View view = null;
            if(convertView == null){
                view = View.inflate(MainActivity.this,R.layout.item_listview,null);
            }else{
                view = convertView;
            }
            TextView textView = (TextView) view.findViewById(R.id.name);
            TextView textView2 = (TextView) view.findViewById(R.id.phone);
            TextView textView3 = (TextView) view.findViewById(R.id.salary);
            textView.setText(person.getName());
            textView2.setText(person.getPhone());
            textView3.setText(person.getSalary()+"");
            return view;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
