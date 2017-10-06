package com.example.smssending;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText phone_number;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view){
        phone_number = (EditText) findViewById(R.id.phone_number);
        content = (EditText) findViewById(R.id.content);
        String phone = phone_number.getText().toString();
        String con = content.getText().toString();

        //直接使用发送短信的API，无需启动系统的短信功能
        SmsManager sm = SmsManager.getDefault();
        //arg0:目标号码
        //arg1:短信中心号码，null表示使用默认
        //arg2:短信正文
        sm.sendTextMessage(phone,null,con,null,null);

    }
}
