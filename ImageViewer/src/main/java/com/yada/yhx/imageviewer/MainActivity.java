package com.yada.yhx.imageviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //只要消息队列有消息，就会执行此方法
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    ImageView imageView = (ImageView) findViewById(R.id.lv);
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                case 2:
                    Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        final String path = "http://192.168.124.11:8080/photo.png";
        final File file = new File(getCacheDir(),getNameFromPath(path));
        if (file.exists()){
            System.out.println("从缓存获取");
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            Message msg = new Message();
            //消息对象本身是可以携带属性的
            msg.obj = bitmap;
            //使用what标注消息是什么类型的
            msg.what = 1;
            handler.sendMessage(msg);
        }else {
            Thread t = new Thread(){
                @Override
                public void run() {
                    System.out.println("从网络获取");
                    //发送http请求
                    try {
                        //1.使用网站构建一个URL对象
                        URL url = new URL(path);
                        //2.获取连接对象
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //3.设置一些属性
                        //设置请求方式，注意大写
                        conn.setRequestMethod("GET");
                        //设置请求超时
                        conn.setConnectTimeout(8000);
                        //设置读取超时
                        conn.setReadTimeout(8000);
                        //4.发送请求，建立连接
                        //conn.connect();
                        if (conn.getResponseCode() == 200){
                            //获取服务器返回的流，流里就是客户端请求的数据
                            InputStream is = conn.getInputStream();

                            //我们自己读取流里的数据，读取1k就把1k写到本地文件缓存起来
                            byte[] b = new byte[1024];
                            int len;

                            FileOutputStream fos = new FileOutputStream(file);
                            while ((len = is.read(b)) != -1){
                                fos.write(b, 0 ,len);
                            }
                            fos.close();
                            //获取流里面的数据，构造出一张图片
                            //Bitmap bitmap = BitmapFactory.decodeStream(is);
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            //当子线程需要刷新UI时，只发送一条消息至消息队列
                            Message msg = new Message();
                            //消息对象本身是可以携带属性的
                            msg.obj = bitmap;
                            //使用what标注消息是什么类型的
                            msg.what = 1;
                            handler.sendMessage(msg);
                            //把下载的图片显示至ImageView
                        /*ImageView imageView = (ImageView) findViewById(R.id.lv);
                        imageView.setImageBitmap(bitmap);*/
                        }else {
                            //如果消息池有消息，取出消息池第一条消息返回，如果没有，就new一个消息返回
                        /*Message msg = handler.obtainMessage();
                        msg.what = 2;
                        handler.sendMessage(msg);*/
                            handler.sendEmptyMessage(2);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }

    public String getNameFromPath(String path){
        int index = path.lastIndexOf("/");
        return path.substring(index + 1 );
    }
}
