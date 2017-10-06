package com.yada.yhx.newsclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.yada.yhx.newsclient.domain.News;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<News> newsList;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ListView lv = (ListView) findViewById(R.id.lv);
            lv.setAdapter(new MyAdapter());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNewsInfo();

        //ListView lv = (ListView) findViewById(R.id.lv);
        //lv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        //返回要显示的条目
        @Override
        public int getCount() {
            return newsList.size();
        }

        //返回一个View对象，会作为ListView的一个条目显示在界面上
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            News news = newsList.get(i);
            View v = null;
            ViewHolder mHolder = null;
            if (view == null){
                v = View.inflate(MainActivity.this,R.layout.item_listview,null);
                //创建viewHolder封装所有条目使用的组件
                mHolder = new ViewHolder();
                mHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
                mHolder.tv_detail = (TextView) v.findViewById(R.id.tv_detail);
                mHolder.tv_comment = (TextView) v.findViewById(R.id.tv_comment);
                mHolder.imageUrl = (SmartImageView) v.findViewById(R.id.iv);
                //把viewHolder封装至View对象中，这样view被缓存时，viewHolder也被缓存了
                v.setTag(mHolder);
            }else {
                v = view;
                //从view中取出viewHolder，viewHolder中就有所有的组件对象，不需要再去findViewById了
                mHolder = (ViewHolder) v.getTag();
            }
            mHolder.tv_title.setText(news.getTitle());
            mHolder.tv_detail.setText(news.getDetail());
            mHolder.tv_comment.setText(news.getComment() + "条评论");
            mHolder.imageUrl.setImageUrl(news.getImageUrl());
            return v;
        }

        @Override
        public Object getItem(int i) {
            return newsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
    }

    //把条目需要使用到的所有组件封装在这个类中
    class ViewHolder{
        TextView tv_title;
        TextView tv_detail;
        TextView tv_comment;
        SmartImageView imageUrl;
    }

    public void getNewsInfo(){
        Thread t = new Thread(){
            @Override
            public void run() {
                String path = "http://192.168.124.11:8080/news.xml";
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    if (conn.getResponseCode() == 200){
                        //流里的信息是一个xml文件的文本信息，用xml解析器去解析，而不要作为文本去解析
                        InputStream is = conn.getInputStream();
                        getNewsFromStream(is);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public void getNewsFromStream(InputStream is){
        XmlPullParser xp = Xml.newPullParser();
        try {
            xp.setInput(is,"utf-8");
            //获取事件类型，通过事件类型判断当前解析的是什么节点
            int type = xp.getEventType();

            News news = null;
            while (type != XmlPullParser.END_DOCUMENT){
                switch (type){
                    case XmlPullParser.START_TAG:
                        if ("newslist".equals(xp.getName())){
                            newsList = new ArrayList<News>();
                        } else if ("news".equals(xp.getName())){
                            news = new News();
                        } else if ("title".equals(xp.getName())){
                            String title = xp.nextText();
                            news.setTitle(title);
                        } else if ("detail".equals(xp.getName())){
                            String detail = xp.nextText();
                            news.setDetail(detail);
                        } else if ("comment".equals(xp.getName())){
                            String comment = xp.nextText();
                            news.setComment(comment);
                        } else if ("image".equals(xp.getName())){
                            String image = xp.nextText();
                            news.setImageUrl(image);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("news".equals(xp.getName())){
                            newsList.add(news);
                        }
                        break;
                }
                //指针移动到下一个节点并返回事件类型
                type = xp.next();
            }
            //发送消息，让主线程刷新ListView
            handler.sendEmptyMessage(1);
            //for (News n : newsList){
                //System.out.println(n.toString());
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
