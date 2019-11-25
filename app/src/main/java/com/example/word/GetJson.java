package com.example.word;

import android.app.DownloadManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetJson {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String get(String q,String from,String to){
        OkHttpClient client = new OkHttpClient();
        String Ur = getUrl(q,from,to);
        Request request=new Request.Builder().url(Ur).build();
        Response response = null;
        String temp = null;
        try{
        response = client.newCall(request).execute();}catch (Exception e){
            e.printStackTrace();
        }
        try{
        temp = response.body().string();
        System.out.println(temp);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            System.out.println("return true");
            return temp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getUrl(String q, String from, String to){
        long time = System.currentTimeMillis();
        String url = "https://openapi.youdao.com/api?";
        url=url+"q="+q+"&";
        url=url+"from="+from+"&";
        url=url+"to="+to+"&";
        url=url+"appKey="+"4eac84ad7ddbb253"+"&";
        url=url+"salt="+String.valueOf(time)+"&";
        url=url+"sign="+getDigest("4eac84ad7ddbb253"+truncate(q)+String.valueOf(time)+String.valueOf(time/1000)+"qpjHp2BO6GgGRsZGOGmPCVcsOp0hfu8r")+"&";
        url=url+"signType="+"v3"+"&";
        url=url+"curtime="+String.valueOf(time/1000);
        return url;
    }


    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = new byte[0];
            btInput = string.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
