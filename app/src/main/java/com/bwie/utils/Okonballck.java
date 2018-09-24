package com.bwie.utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class Okonballck implements Callback {
    private Handler handler=OkHttpUtils.getOkHttpUtils().getHandler();
    public  abstract void onfiled(Call call, IOException e);
    public  abstract void onsuccess(String result);

    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onfiled(call, e);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result= response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                onsuccess(result);
            }
        });
    }
}
