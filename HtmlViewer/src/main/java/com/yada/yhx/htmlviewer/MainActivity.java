package com.yada.yhx.htmlviewer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yada.yhx.htmlviewer.tools.Tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView textView = (TextView) findViewById(R.id.tv);
            textView.setText((String) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        final String path = "http://192.168.124.11:8080/baidu.htm";
        Thread t = new Thread(){
            @Override
            public void run() {
                //使用网站构造URL
                URL url;
                try {
                    url = new URL(path);
                    //获取连接对象，做设置
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    if (conn.getResponseCode() == 200){
                        //获取服务器返回的流
                        InputStream is = conn.getInputStream();
                        String text = Tools.getTextFromStream(is);
                        Message msg = new Message();
                        msg.obj = text;
                        handler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
