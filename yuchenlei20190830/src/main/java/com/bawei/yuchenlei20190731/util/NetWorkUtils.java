package com.bawei.yuchenlei20190731.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 网络请求
 * 于晨雷
 * 2019-7-31 09:56:32
 */
public class NetWorkUtils {

//    单例
    private static NetWorkUtils netWorkUtils=new NetWorkUtils();

    private NetWorkUtils(){

    }
    public static NetWorkUtils netWorkUtils(){
        return netWorkUtils;
    }
//    网络判断
    private Handler handler=new Handler();
    public boolean NetWork(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
//    接口
    public interface  mCallBack{
        void getSer(Object obj);
        void getEro(String mes);
    }
//    处理异步数据
    public void getData(final String path, final mCallBack mCallBack){
new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            URL url = new URL(path);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len=0;
                while ((len=inputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,len);
                }
                final String json = outputStream.toString();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.getSer(json);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}).start();

    }
    //    处理异步加载图片
    public void getBimData(final String path, final mCallBack mCallBack){
        new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            URL url = new URL(path);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.getSer(bitmap);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}).start();

    }

}
