package com.yada.yhx.htmlviewer.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yhx on 2017/10/3.
 */

public class Tools {

    public static String getTextFromStream(InputStream is){
        byte[] b = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(b)) != -1){
                bos.write(b,0,len);
            }
            //把字符数组输出流转换成字节数组，然后用字节数组构造一个字符串
            String text = new String(bos.toByteArray(),"utf-8");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
