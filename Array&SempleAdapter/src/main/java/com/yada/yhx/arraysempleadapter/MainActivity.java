package com.yada.yhx.arraysempleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] objects = new String[]{"白吃","沙比","春晓"};
        listView = (ListView)findViewById(R.id.lv);
        /*listView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview,//指定作为条目的布局文件
                R.id.lv,//指定文本显示在哪个文本框
                objects));*/
        //把每个条目需要处理的所有数据封装至map中，再把map封装至list中，这样就保证了list每个元素都包含一个条目需要的所有数据
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name","周杰伦");
        map1.put("photo",R.drawable.p1);
        data.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name","刘德华");
        map2.put("photo",R.drawable.p2);
        data.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name","周星驰");
        map3.put("photo",R.drawable.p3);
        data.add(map3);

        listView.setAdapter(new SimpleAdapter(this,data,R.layout.item_listview,
                new String[]{"name","photo"},//存放键的数组
                new int[]{R.id.tv,R.id.iv}));
    }
}
