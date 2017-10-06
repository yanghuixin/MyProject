package com.example.generatexml;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sms> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsList = new ArrayList<Sms>();
        for (int i = 0; i < 10; i++){
            Sms sms = new Sms("你好", System.currentTimeMillis(), 1, "18513054792");
            smsList.add(sms);
        }
    }

    public void onclick(View view) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        sb.append("<smss>");
//        for (Sms sms : smsList){
//            sb.append("<sms>");
//
//            sb.append("<body>");
//            sb.append(sms.getBody());
//            sb.append("</body>");
//
//            sb.append("<date>");
//            sb.append(sms.getDate());
//            sb.append("</date>");
//
//            sb.append("<type>");
//            sb.append(sms.getType());
//            sb.append("</type>");
//
//            sb.append("<address>");
//            sb.append(sms.getAddress());
//            sb.append("</address>");
//
//            sb.append("</sms>");
//        }
//        sb.append("</smss>");
//        File file = new File("sdcard/sms.xml");
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            try {
//                fos.write(sb.toString().getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        //获取xml序列化器
        XmlSerializer xs = Xml.newSerializer();
        File file = new File("sdcard/sms.xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //初始化
            //xml用什么编码生成
            xs.setOutput(fos,"utf-8");
            //开始生成xml文件
            //生成头结点
            xs.startDocument("utf-8", true);

            //生成开始标签
            xs.startTag(null , "smss");
            for (Sms sms : smsList){
                xs.startTag(null, "sms");

                xs.startTag(null, "body");
                xs.text(sms.getBody());
                xs.endTag(null, "body");

                xs.startTag(null, "date");
                xs.text(String.valueOf(sms.getDate()));
                xs.endTag(null, "date");

                xs.startTag(null, "type");
                xs.text(String.valueOf(sms.getType()));
                xs.endTag(null, "type");

                xs.startTag(null, "address");
                xs.text(sms.getAddress());
                xs.endTag(null, "address");

                xs.endTag(null, "sms");
            }
            //生成结束标签
            xs.endTag(null ,"smss");

            //生成尾节点，告知序列化器生成xml结束
            xs.endDocument();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
