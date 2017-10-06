package com.example.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences sp = getSharedPreferences("info",MODE_PRIVATE);
        String name = sp.getString("name","");
        String pass = sp.getString("pass","");
        et_name.setText(name);
        et_pass.setText(pass);
    }

    public void login(View view) {
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        if (checkBox.isChecked()){
            //获取到sharedpreference
            SharedPreferences sp = getSharedPreferences("info",MODE_PRIVATE);
            //获取编辑器
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name",name);
            editor.putString("pass",pass);
            editor.commit();
        }
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }
}
