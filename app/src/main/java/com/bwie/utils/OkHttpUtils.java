package com.bwie.utils;

import android.os.Handler;
import android.os.Looper;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
        private  Handler handler=new Handler(Looper.myLooper());
        public Handler getHandler(){
            return handler;
        }
        private  static OkHttpUtils okHttpUtils=new OkHttpUtils();
        private  OkHttpUtils(){};
        public static OkHttpUtils getOkHttpUtils(){
             return okHttpUtils;
        }
        private  OkHttpClient client;
        public  void  initOkHttp(){
               if (client==null){
                     client=new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
               }
        }
        public void doGet(String url, Callback callback){
               initOkHttp();
               Request request=new Request.Builder().addHeader("As-age","").url(url).build();
            Call call=client.newCall(request);
            call.enqueue(callback);
        }
}
