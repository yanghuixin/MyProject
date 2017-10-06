package com.example.administrator.myproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            textView = (TextView) findViewById(R.id.text);
            String number= textView.getText().toString();
            //创建意图
            Intent intent = new Intent();
            //把动作封装到意图中
            intent.setAction(Intent.ACTION_CALL);
            //打电话打给谁
            intent.setData(Uri.parse("tel:"+number));
            //告诉系统我的动作
            startActivity(intent);
        }
    }
}
