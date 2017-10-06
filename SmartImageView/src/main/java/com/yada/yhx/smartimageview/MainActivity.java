package com.yada.yhx.smartimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.loopj.android.image.SmartImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        SmartImageView smartImageView = (SmartImageView) findViewById(R.id.lv);
        //请求网络图片
        smartImageView.setImageUrl("http://192.168.124.11:8080/photo.png");
    }
}
