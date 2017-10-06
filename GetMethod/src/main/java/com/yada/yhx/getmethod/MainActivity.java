package com.yada.yhx.getmethod;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yada.yhx.getmethod.Tools.Tools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MainActivity.this,(String)msg.obj,Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_pass = (EditText) findViewById(R.id.et_pass);
        final String name = et_name.getText().toString();
        final String pass = et_pass.getText().toString();
        Thread t = new Thread(){
            @Override
            public void run() {
                String path = "http://192.168.124.11:8080/Web/servlet/Login?name=" + URLEncoder.encode(name) + "&pass=" + pass;
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    if (conn.getResponseCode() == 200){
                        InputStream is = conn.getInputStream();
                        String text = Tools.getTextFromStream(is);
                        Message message = handler.obtainMessage();
                        message.obj = text;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
