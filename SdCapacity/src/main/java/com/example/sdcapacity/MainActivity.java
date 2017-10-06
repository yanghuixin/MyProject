package com.example.sdcapacity;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        getSdCapacity();
    }

    public void getSdCapacity(){
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize;
        long availableBlocks;
        //判断当前版本是否是4.3或以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            blockSize = stat.getBlockSizeLong();
            availableBlocks = stat.getAvailableBlocksLong();
        }else{
            blockSize = stat.getBlockSize();
            availableBlocks = stat.getAvailableBlocks();
        }
        String text = formatSize(availableBlocks * blockSize);
        textView.setText(text);
    }

    public String formatSize(long size){
        return Formatter.formatFileSize(this,size);
    }
}
