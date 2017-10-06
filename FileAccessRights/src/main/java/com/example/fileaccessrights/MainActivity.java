package com.example.fileaccessrights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick1(View view) {
        //路径默认为data/data/com.example.fileaccessrights/files
        try {
            FileOutputStream fos = openFileOutput("info1.txt",MODE_PRIVATE);
            fos.write("hahaha".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onclick2(View view) {
        //路径默认为data/data/com.example.fileaccessrights/files
        try {
            FileOutputStream fos = openFileOutput("info2.txt",MODE_WORLD_READABLE);
            fos.write("lalala".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onclick3(View view) {
        //路径默认为data/data/com.example.fileaccessrights/files
        try {
            FileOutputStream fos = openFileOutput("info3.txt",MODE_WORLD_WRITEABLE | MODE_WORLD_READABLE);
            fos.write("ohohoh".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
