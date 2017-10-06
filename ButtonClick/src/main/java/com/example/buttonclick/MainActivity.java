package com.example.buttonclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("按钮1");
            }
        });

        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        System.out.println("按钮2");
    }

    public void click(View view){
        int id = view.getId();
        switch (id){
            case R.id.button3:
                System.out.println("按钮3");
                break;
            case R.id.button4:
                System.out.println("按钮4");
                break;
            case R.id.button5:
                System.out.println("按钮5");
                break;

        }
    }
}
