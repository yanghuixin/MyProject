package com.example.writefile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pass;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        readAccount();
    }

    public void readAccount(){
        //File file = new File("data/data/com.example.writefile/info.txt");
        File file = new File(getFilesDir(),"info.txt");
        if(file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                //把字节流转换成字符流
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //读取文件中的文本
                String text = br.readLine();
                String[] s = text.split("&&");
                //给输入框设置文本
                et_name.setText(s[0]);
                et_pass.setText(s[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void login(View view) {
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        if (checkBox.isChecked()){
            //File file = new File("data/data/com.example.writefile/info.txt");
            File file = new File(getFilesDir(),"info.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write((name + "&&" + pass).getBytes());
                System.out.println("保存的用户名密码为：" + name + pass);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        Intent  intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
